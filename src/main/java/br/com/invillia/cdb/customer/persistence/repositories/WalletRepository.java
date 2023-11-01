package br.com.invillia.cdb.customer.persistence.repositories;

import br.com.invillia.cdb.customer.persistence.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
}
