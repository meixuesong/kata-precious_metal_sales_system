package com.coding.sales.representation;

import java.math.BigDecimal;
import java.util.Objects;

public class PaymentRepresentation {
    private String type;
    private BigDecimal amount;

    public PaymentRepresentation(String type, BigDecimal amount) {
        this.type = type;
        this.amount = amount == null ? BigDecimal.ZERO : amount;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRepresentation that = (PaymentRepresentation) o;

        return type.equals(that.type) &&
                amount.compareTo(that.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount);
    }
}
