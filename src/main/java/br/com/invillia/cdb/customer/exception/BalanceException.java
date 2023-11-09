package br.com.invillia.cdb.customer.exception;

import br.com.invillia.cdb.customer.exception.enums.BalanceEnumException;

public class BalanceException extends RuntimeException {
    public BalanceException(BalanceEnumException enumException) {
        super(String.format("Code %s: %s", enumException.label, enumException.message));
    }
}
