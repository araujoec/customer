package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.domain.Customer;
import br.com.invillia.cdb.customer.domain.Wallet;
import br.com.invillia.cdb.customer.exception.WalletException;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import br.com.invillia.cdb.customer.persistence.entities.WalletEntity;
import br.com.invillia.cdb.customer.persistence.repositories.CustomerRepository;
import br.com.invillia.cdb.customer.persistence.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    CustomerRepository customerRepository;

    private WalletEntity getWalletByCustomer(Customer customer) {
        CustomerEntity customerEntity = customerRepository.findByDocument(customer.getDocument());

        if (customerEntity == null) {
            throw new WalletException("Wallet not found because this customer doesn't exist.");
        } else {
            return walletRepository.findById(customerEntity.getId()).get();
        }

    }

    public void buyCDB(Customer customer, long amount) {
        WalletEntity walletEntity = getWalletByCustomer(customer);

        if (walletEntity.getBalance() >= amount) {
            walletEntity.setBalance(walletEntity.getBalance() - amount);
            walletEntity.setPaper(walletEntity.getPaper() + amount);
        } else {
            throw new WalletException("Valor de CDB acima do saldo.");
        }

        walletRepository.save(walletEntity);
    }

    public void sellCDB(Customer customer, long amount) {
        WalletEntity walletEntity = getWalletByCustomer(customer);

        if (walletEntity.getPaper() >= amount) {
            walletEntity.setBalance(walletEntity.getBalance() + amount);
            walletEntity.setPaper(walletEntity.getPaper() - amount);
        } else {
            throw new WalletException("Saldo abaixo de CDB (que pretende vender).");
        }

        walletRepository.save(walletEntity);
    }

    public void increaseBalance(Wallet wallet, long amount) {
        wallet.increaseBalance(amount);
    }
}
