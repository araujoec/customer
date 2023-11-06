package br.com.invillia.cdb.customer.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paper {

    private final String customerDocument;
    private Long amount;

    public Paper(String customerDocument, Long amount) {
        this.customerDocument = customerDocument;
        this.amount = setAmount(amount);
    }

    public Long setAmount(Long amount) {
        if (amount <= 0) {
            return this.amount;
        } else {
            return amount;
        }
    }

}
