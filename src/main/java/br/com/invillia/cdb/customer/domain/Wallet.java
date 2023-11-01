package br.com.invillia.cdb.customer.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wallet {

    private Long balance;
    private Long paper;

    public Wallet(Long balance) {
        this.balance = balance;
    }

    public Wallet(Long balance, Long paper) {
        this.balance = balance;
        this.paper = paper;
    }
}
