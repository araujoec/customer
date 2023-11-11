package br.com.invillia.cdb.customer.exception.enums;

public enum BalanceEnumException {

    BALANCE_NOT_FOUND("balance-exception-001", "Saldo não encontrado para id de cliente fornecido.");

    public final String label;
    public final String message;

    private BalanceEnumException(String label, String message) {
        this.label = label;
        this.message = message;
    }
}
