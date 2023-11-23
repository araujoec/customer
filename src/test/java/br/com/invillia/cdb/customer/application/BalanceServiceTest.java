package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.domain.Balance;
import br.com.invillia.cdb.customer.exception.BalanceException;
import br.com.invillia.cdb.customer.persistence.entities.BalanceEntity;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import br.com.invillia.cdb.customer.persistence.repositories.BalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    private CustomerEntity customerEntity;

    private String transactionId;


    @BeforeEach
    void setup() {
        customerEntity = new CustomerEntity("Name", "12345678910", "name@email.com", null);
        customerEntity.setId(UUID.randomUUID().toString());
        transactionId = UUID.randomUUID().toString();
    }

    @DisplayName("Create balance for customer")
    @Test
    public void createBalanceForCustomer() {
        // given a new customer
        // and a new balance
        Double balance = 10.0;


        // stubbing balance repository behavior
        when(balanceRepository.save(any(BalanceEntity.class)))
                .thenReturn(new BalanceEntity(customerEntity, balance));

        // when call create balance for customer method
        BalanceEntity balanceEntity = balanceService.createBalanceForCustomer(customerEntity, balance, transactionId);

        // then balance entity is not null
        assertNotNull(balanceEntity);

        // and balance entity fields matches customer's fields
        assertEquals(balanceEntity.getBalance(), balance);
    }

    @DisplayName("Try to update balance but exception will be thrown")
    @Test
    public void updateTestAndThrowException() {
        // given a balance
        Balance balance = new Balance("customerId", 10.0);

        // stubbing balance repository behavior
        when(balanceRepository.findByCustomerId(balance.getCustomerId()))
                .thenReturn(null);

        // when call update balance method
        // then exception will be thrown
        BalanceException exception = assertThrows(
                BalanceException.class,
                () -> balanceService.updateBalance(balance, transactionId)
        );

        // and exception message is correct
        assertEquals(exception.getMessage(), "Code balance-exception-001: Saldo não encontrado para id de cliente fornecido.");
    }

    @DisplayName("Update balance successfully")
    @Test
    public void updateTest() {
        // given a balance
        Balance balance = new Balance(customerEntity.getId(), 10.0);

        // stubbing balance repository behavior
        when(balanceRepository.findByCustomerId(balance.getCustomerId()))
                .thenReturn(new BalanceEntity(customerEntity, 0.0));

        // when call update balance method
        Balance updatedBalance = balanceService.updateBalance(balance, transactionId);

        // then balance will be updated
        assertEquals(balance.getBalance(), updatedBalance.getBalance());
    }


}