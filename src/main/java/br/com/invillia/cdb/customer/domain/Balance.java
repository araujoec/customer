package br.com.invillia.cdb.customer.domain;

import lombok.Getter;

@Getter
public class Balance {

    private final String customerId;
    private final Double balance;

    public Balance(String customerId, Double balance) {
        this.customerId = customerId;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
                "customerId='" + customerId + '\'' +
                ", balance=" + balance +
                '}';
    }
}
