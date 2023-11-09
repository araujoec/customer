package br.com.invillia.cdb.customer.exception;

import br.com.invillia.cdb.customer.exception.enums.CustomerEnumException;

public class CustomerException extends RuntimeException {
    public CustomerException(CustomerEnumException enumException) {
        super(String.format("Code %s: %s", enumException.label, enumException.message));
    }
}
