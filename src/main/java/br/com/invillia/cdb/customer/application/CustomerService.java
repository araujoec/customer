package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.exception.CustomerException;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import br.com.invillia.cdb.customer.persistence.entities.WalletEntity;
import br.com.invillia.cdb.customer.persistence.repositories.CustomerRepository;
import br.com.invillia.cdb.customer.persistence.repositories.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    //    @Autowired
    private final CustomerRepository customerRepository;

    //    @Autowired
    private final WalletRepository walletRepository;
    private Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    public CustomerService(CustomerRepository customerRepository, WalletRepository walletRepository) {
        this.customerRepository = customerRepository;
        this.walletRepository = walletRepository;
    }

    public CustomerEntity createCustomer(String name, String document, String email, Long balance) {
        if (customerRepository.findByDocument(document).isPresent()) {
            logger.warn(String.format("Cliente já existente para documento %s.", document));
            throw new CustomerException(String.format("Cliente já existente para documento %s.", document));
        }

        WalletEntity newWallet = new WalletEntity(balance);
        CustomerEntity newCustomer = new CustomerEntity(
                name,
                document,
                email,
                newWallet
        );
        newWallet.setCustomer(newCustomer);
        walletRepository.save(newWallet);
        customerRepository.save(newCustomer);

        return newCustomer;
    }
}
