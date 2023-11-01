package br.com.invillia.cdb.customer.persistence.entities;

import br.com.invillia.cdb.customer.domain.Customer;
import br.com.invillia.cdb.customer.domain.Wallet;
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
    @Column(name = "id")
    private Long id = 0L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private WalletEntity wallet;

    public CustomerEntity(String name, String document, String email, WalletEntity wallet) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.wallet = wallet;
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
        return new Customer(name, document, email, new Wallet(wallet.getBalance(), wallet.getPaper()));
    }
}
