package br.com.invillia.cdb.customer.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Customer {

    private String name;
    private String document;
    private String email;
    private Wallet wallet;

    public Customer(String name, String document, String email, Wallet wallet) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(document, customer.document) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, document, email);
    }
}
