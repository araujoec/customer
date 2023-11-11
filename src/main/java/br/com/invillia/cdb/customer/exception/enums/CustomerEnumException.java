package br.com.invillia.cdb.customer.exception.enums;

public enum CustomerEnumException {

    CUSTOMER_NOT_FOUND("customer-exception-001", "Cliente não encontrado no banco de dados."),
    CUSTOMER_EXISTS("customer-exception-002", "Cliente já existe para documento fornecido."),
    BALANCE_LOWER_THAN_ZERO("customer-exception-003", "Saldo de cliente insuficiente.");

    public final String label;
    public final String message;


    private CustomerEnumException(String label, String message) {
        this.label = label;
        this.message = message;
    }

}
