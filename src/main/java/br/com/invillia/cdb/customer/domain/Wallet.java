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
        this.paper = 0L;
    }

    public Wallet(Long balance, Long paper) {
        this.balance = balance;
        this.paper = paper;
    }

    public void buyCDB(long amount) {
        if (balance >= amount) {
            balance -= amount;
            paper += amount;
        }
    }

    public void sellCDB(long amount) {
        if (paper >= amount) {
            balance += amount;
            paper -= amount;
        }
    }

    public void increaseBalance(long amount) {
        this.balance += amount;
    }

}
