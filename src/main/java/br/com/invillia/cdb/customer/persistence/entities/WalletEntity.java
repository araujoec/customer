package br.com.invillia.cdb.customer.persistence.entities;

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


    public WalletEntity(Long balance) {
        this.balance = balance;
        this.paper = 0L;
    }

}
