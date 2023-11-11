package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.domain.Balance;
import br.com.invillia.cdb.customer.domain.Customer;
import br.com.invillia.cdb.customer.exception.CustomerException;
import br.com.invillia.cdb.customer.exception.enums.CustomerEnumException;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import br.com.invillia.cdb.customer.persistence.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BalanceService balanceService;

    public Customer createCustomer(String name, String document, String email, Double balance) {
        String transactionId = UUID.randomUUID().toString();
        log.debug("Creating customer: name = {}, document = {}, email = {}, balance = {}", name, document, email, balance);
        log.info("Transaction id: {}", transactionId);

        if (balance < 0) {
            log.warn("[{}] Balance equal or lower than zero ({})", transactionId, balance);
            throw new CustomerException(CustomerEnumException.BALANCE_LOWER_THAN_ZERO);
        }

        if (customerRepository.findByDocument(document) != null) {
            log.warn("[{}] Customer already defined for this document {}", transactionId, document);
            throw new CustomerException(CustomerEnumException.CUSTOMER_EXISTS);
        }

        Balance newBalance = new Balance(document, balance);
        Customer newCustomer = new Customer(name, document, email, newBalance);

        log.debug("[{}] Saving customer to database...", transactionId);
        balanceService.saveBalanceForCustomer(
                customerRepository.save(CustomerEntity.fromDomain(newCustomer)),
                balance,
                transactionId
        );

        log.info("[{}] Customer created with success: {}", transactionId, newCustomer);
        return newCustomer;
    }

    public Customer getCustomer(String document, String transactionId) {
        log.debug("[{}] Searching customer by document ({})...", transactionId, document);
        CustomerEntity customer = customerRepository.findByDocument(document);

        if (customer == null) {
            log.warn("[{}] Customer not found in database for document = {}", transactionId, document);
            throw new CustomerException(CustomerEnumException.CUSTOMER_NOT_FOUND);
        } else {
            return customer.toDomain();
        }
    }
}
