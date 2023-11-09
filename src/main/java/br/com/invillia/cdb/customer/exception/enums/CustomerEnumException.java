package br.com.invillia.cdb.customer.exception.enums;

public enum CustomerEnumException {

    CUSTOMER_NOT_FOUND("cex-001", "Customer not found in database."),
    CUSTOMER_EXISTS("cex-002", "Customer already in database."),
    BALANCE_LOWER_THAN_ZERO("cex-003", "Saldo negativo.");

    public final String label;
    public final String message;


    private CustomerEnumException(String label, String message) {
        this.label = label;
        this.message = message;
    }

}
