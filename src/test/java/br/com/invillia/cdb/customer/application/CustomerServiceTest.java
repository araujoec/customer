package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.domain.Customer;
import br.com.invillia.cdb.customer.exception.CustomerException;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import br.com.invillia.cdb.customer.persistence.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private CustomerService customerService;

    private String name;
    private String document;
    private String email;
    private Double balance;

    @BeforeEach
    public void setup() {
        name = "Name";
        document = "document";
        email = "email@mail.com";
    }

    @DisplayName("Create a customer and throw balance lower than zero exception")
    @Test
    public void createCustomerAndThrowBalanceLowerThanZeroException() {
        // given a balance lower than zero
        balance = -1.0;

        // when trying to create a new customer
        // then a balance lower than zero exception should be thrown
        CustomerException exception = assertThrows(
                CustomerException.class,
                () -> customerService.createCustomer(name, document, email, balance)
        );

        // and exception message is correct
        assertEquals(exception.getMessage(), "Code customer-exception-003: Saldo de cliente insuficiente.");
    }

    @DisplayName("Create a customer and throw customer exists exception")
    @Test
    public void createCustomerAndThrowCustomerExistsException() {
        // given a customer already defined in database

        // (stubbing repository behavior)
        when(customerRepository.findByDocument(document))
                .thenReturn(new CustomerEntity(name, document, email, null));

        // when trying to create a new customer
        // then a customer exists exception should be thrown
        CustomerException exception = assertThrows(
                CustomerException.class,
                () -> customerService.createCustomer(name, document, email, balance = 1.0)
        );

        // and exception message is correct
        assertEquals(exception.getMessage(), "Code customer-exception-002: Cliente já existe para documento fornecido.");
    }

    @DisplayName("Create a customer")
    @Test
    public void createCustomer() {
        // given a customer and a balance
        balance = 0.0;

        // when trying to create a new customer
        Customer newCustomer = customerService.createCustomer(name, document, email, balance);

        // then new customer is created
        assertEquals(newCustomer.getName(), name);
        assertEquals(newCustomer.getDocument(), document);
        assertEquals(newCustomer.getEmail(), email);
        assertEquals(newCustomer.getBalance().getBalance(), balance);
    }

    @DisplayName("Get customer and throw customer not found exception")
    @Test
    public void getCustomerAndThrowCustomerNotFoundException() {
        // given a customer document
        // and a transaction id
        String transactionId = UUID.randomUUID().toString();

        // when trying to get a customer
        // then a customer not found exception should be thrown
        CustomerException exception = assertThrows(
                CustomerException.class,
                () -> customerService.getCustomer(document, transactionId)
        );

        // and exception message is correct
        assertEquals(exception.getMessage(), "Code customer-exception-001: Cliente não encontrado no banco de dados.");
    }

    @DisplayName("Get customer successfully")
    @Test
    public void getCustomerSuccessfully() {
        // given a customer document
        // and a transaction id
        String transactionId = UUID.randomUUID().toString();

        // (stubbing repository behavior)
        when(customerRepository.findByDocument(document))
                .thenReturn(new CustomerEntity(name, document, email, null));

        // when trying to get a customer
        // then a customer is found
        Customer customerFound = customerService.getCustomer(document, transactionId);

        assertNotNull(customerFound);
    }
}