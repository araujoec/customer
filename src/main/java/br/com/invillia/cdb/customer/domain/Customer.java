package br.com.invillia.cdb.customer.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Customer {

    private String name;
    private String document;
    private String email;
    private Balance balance;

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
