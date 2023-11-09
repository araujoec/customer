package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.domain.Balance;
import br.com.invillia.cdb.customer.domain.Customer;
import br.com.invillia.cdb.customer.exception.enums.CustomerEnumException;
import br.com.invillia.cdb.customer.exception.CustomerException;
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
        String uniqueID = UUID.randomUUID().toString();
        log.info("Operation id: {}", uniqueID);

        if (balance < 0) {
            log.warn("Valor de balance não aceitável. {}", balance);
            throw new CustomerException(CustomerEnumException.BALANCE_LOWER_THAN_ZERO);
        }

        if (customerRepository.findByDocument(document) != null) {
            log.warn(String.format("Cliente já existente para documento %s.", document));
            throw new CustomerException(CustomerEnumException.CUSTOMER_EXISTS);
        }

        Balance newBalance = new Balance(document, balance);
        Customer newCustomer = new Customer(name, document, email, newBalance);

        log.info("Saving customer to database...");

        balanceService.saveBalanceForCustomer(
                customerRepository.save(CustomerEntity.fromDomain(newCustomer)),
                balance
        );

        log.info("Customer created with success.\nOperation id: {}\n{}", uniqueID, newCustomer);
        return newCustomer;
    }

    public Customer getCustomer(String document, String operationId) {
        if (operationId != null) {
            log.info("Retrieving customer of document {} for operation {}...", document, operationId);
        }
        CustomerEntity customer = customerRepository.findByDocument(document);

        if (customer == null) {
            log.warn("Customer for document {} not found in database.", document);
            throw new CustomerException(CustomerEnumException.CUSTOMER_NOT_FOUND);
        } else {
            return customer.toDomain();
        }
    }
}
