package br.com.invillia.cdb.customer.persistence.repositories;

import br.com.invillia.cdb.customer.domain.Customer;
import br.com.invillia.cdb.customer.persistence.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

//    @Query("SELECT w FROM Wallet w INNER JOIN Customer c ON w.customer_id = c.id WHERE c.id=#{#customer.document}")
//    public WalletEntity findByCustomer(@Param("customer") Customer customer);

}
