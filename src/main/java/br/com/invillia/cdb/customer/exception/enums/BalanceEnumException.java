package br.com.invillia.cdb.customer.exception.enums;

public enum BalanceEnumException {

    BALANCE_NOT_FOUND("bex-001", "Balance not found in database for customer id.");

    public final String label;
    public final String message;

    private BalanceEnumException(String label, String message) {
        this.label = label;
        this.message = message;
    }
}
