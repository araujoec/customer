package br.com.invillia.cdb.customer.persistence.repositories;

import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    public CustomerEntity findByDocument(String document);
}
