package br.com.invillia.cdb.customer.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wallet {

    private Customer customer;
    private Long balance;
    private Long paper;

    public Wallet(Customer customer, Long balance) {
        this.customer = customer;
        this.balance = balance;
    }
}
