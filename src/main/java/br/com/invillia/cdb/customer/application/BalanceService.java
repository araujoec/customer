package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.domain.Balance;
import br.com.invillia.cdb.customer.exception.enums.BalanceEnumException;
import br.com.invillia.cdb.customer.exception.BalanceException;
import br.com.invillia.cdb.customer.persistence.entities.BalanceEntity;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import br.com.invillia.cdb.customer.persistence.repositories.BalanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    public void saveBalanceForCustomer(CustomerEntity customer, Double balance) {
        log.info("Saving balance to database...");
        BalanceEntity newBalance = new BalanceEntity();
        newBalance.setBalance(balance);
        newBalance.setCustomer(customer);
        balanceRepository.save(newBalance);
    }

    public Balance updateBalance(Balance balance, String operationId) {
        if (operationId != null) {
            log.info("Updating balance customer for operation {}...", operationId);
        }
        BalanceEntity balanceEntity = balanceRepository.findByCustomerId(balance.getCustomerId());

        if (balanceEntity == null) {
            log.warn("Balance for customer id {} not found in database.", balance.getCustomerId());
            throw new BalanceException(BalanceEnumException.BALANCE_NOT_FOUND);
        }
        balanceEntity.setBalance(balance.getBalance());
        balanceRepository.save(balanceEntity);
        log.info("Balance updated in database.");
        return balanceEntity.toDomain();
    }
}
