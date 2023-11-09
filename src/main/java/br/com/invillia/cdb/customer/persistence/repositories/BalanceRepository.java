package br.com.invillia.cdb.customer.persistence.repositories;

import br.com.invillia.cdb.customer.persistence.entities.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceEntity, String> {
    public BalanceEntity findByCustomerId(String customerId);
}
