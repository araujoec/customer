package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.exception.CustomerException;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import br.com.invillia.cdb.customer.persistence.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity createCustomer(String name, String document, String email) {
        CustomerEntity newCustomer = new CustomerEntity(name, document, email);

        if (customerRepository.findByDocument(document).isPresent()) {
            throw new CustomerException(String.format("Cliente já existente para documento %s.", document));
        }

        return customerRepository.save(new CustomerEntity(name, document, email));
    }
}
