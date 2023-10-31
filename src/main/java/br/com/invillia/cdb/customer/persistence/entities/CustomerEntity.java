package br.com.invillia.cdb.customer.persistence.entities;

import br.com.invillia.cdb.customer.domain.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "document", nullable = false)
    private String document;
    @Column(name = "email", nullable = false)
    private String email;

    public CustomerEntity(String name, String document, String email) {
        this.name = name;
        this.document = document;
        this.email = email;
    }

    public CustomerEntity(Long id, String name, String document, String email) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
    }

    public Customer toDomain() {
        return new Customer(name, document, email);
    }

    public static CustomerEntity fromDomain(Customer customer) {
        return new CustomerEntity(
                customer.getName(),
                customer.getDocument(),
                customer.getEmail()
        );
    }
}
