package br.com.invillia.cdb.customer.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Balance {

    private final String customerId;
    private Double balance;

    public Balance(String customerId, Double balance) {
        this.customerId = customerId;
        this.balance = balance;
    }

    public Balance(String customerId) {
        this.customerId = customerId;
        this.balance = 0.0;
    }
}
