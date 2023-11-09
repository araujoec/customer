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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BalanceEntity balance;

    public CustomerEntity(String name, String document, String email, BalanceEntity balance) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.balance = balance;
    }

    public static CustomerEntity fromDomain(Customer customer) {
        return new CustomerEntity(
                customer.getName(),
                customer.getDocument(),
                customer.getEmail(),
                null
        );
    }

    public Customer toDomain() {
        if (balance != null) {
            return new Customer(name, document, email, balance.toDomain());
        }
        return new Customer(name, document, email, null);
    }
}
