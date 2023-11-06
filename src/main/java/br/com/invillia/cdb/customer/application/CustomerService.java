package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.domain.Customer;
import br.com.invillia.cdb.customer.domain.Wallet;
import br.com.invillia.cdb.customer.exception.CustomerException;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import br.com.invillia.cdb.customer.persistence.entities.WalletEntity;
import br.com.invillia.cdb.customer.persistence.repositories.CustomerRepository;
import br.com.invillia.cdb.customer.persistence.repositories.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final WalletRepository walletRepository;

    public CustomerService(CustomerRepository customerRepository, WalletRepository walletRepository) {
        this.customerRepository = customerRepository;
        this.walletRepository = walletRepository;
    }

    public Customer createCustomer(String name, String document, String email, Long balance) {
        String uniqueID = UUID.randomUUID().toString();
        log.info("ID da operação: {}", uniqueID);
        if (customerRepository.findByDocument(document) != null) {
            log.warn(String.format("Cliente já existente para documento %s.", document));
            throw new CustomerException(String.format("Cliente já existente para documento %s.", document));
        }

        Wallet newWallet = new Wallet(balance);
        Customer newCustomer = new Customer(name, document, email, newWallet);

        walletRepository.save(WalletEntity.fromDomain(newWallet, newCustomer));

        return newCustomer;
    }

    public Customer getCustomer(String document) {
        CustomerEntity customer = customerRepository.findByDocument(document);

        if (customer == null) {
            throw new CustomerException("customer not found");
        } else {
            return customer.toDomain();
        }
    }
}
