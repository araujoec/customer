package br.com.invillia.cdb.customer.persistence.entities;

import br.com.invillia.cdb.customer.domain.Customer;
import br.com.invillia.cdb.customer.domain.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@NoArgsConstructor
public class WalletEntity {

    @Id
    @Column(name = "customer_id")
    private Long id;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "paper")
    private Long paper;


    public WalletEntity(CustomerEntity customer, long balance, long paper) {
        this.customer = customer;
        this.balance = balance;
        this.paper = paper;
    }

    public Wallet toDomain() {
        return new Wallet(balance, paper);
    }

    public static WalletEntity fromDomain(Wallet wallet, Customer customer) {
        return new WalletEntity(
                CustomerEntity.fromDomain(customer),
                wallet.getBalance(),
                wallet.getPaper()
        );
    }
}
