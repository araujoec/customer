package br.com.invillia.cdb.customer.persistence.entities;

import br.com.invillia.cdb.customer.domain.Balance;
import br.com.invillia.cdb.customer.domain.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "balance")
@Getter
@Setter
@NoArgsConstructor
public class BalanceEntity {

    @Id
    @Column(name = "customer_id")
    private String id;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @Column(name = "balance")
    private Double balance;

    public BalanceEntity(CustomerEntity customer, Double balance) {
        this.customer = customer;
        this.balance = balance;
    }

    public Balance toDomain() {
        return new Balance(customer.getId(), balance);
    }

    public static BalanceEntity fromDomain(Balance balance, Customer customer) {
        return new BalanceEntity(
                CustomerEntity.fromDomain(customer),
                balance.getBalance()
        );
    }
}
