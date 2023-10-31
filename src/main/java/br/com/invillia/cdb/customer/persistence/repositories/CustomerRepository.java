package br.com.invillia.cdb.customer.persistence.repositories;

import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    public Optional<CustomerEntity> findByDocument(String document);
}
