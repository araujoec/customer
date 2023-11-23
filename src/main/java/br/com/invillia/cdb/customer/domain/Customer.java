package br.com.invillia.cdb.customer.domain;

import lombok.Getter;

@Getter
public class Customer {

    private final String name;
    private final String document;
    private final String email;
    private final Balance balance;

    public Customer(String name, String document, String email, Balance balance) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance.getBalance() +
                '}';
    }
}
