package com.coding.sales.output;

import java.math.BigDecimal;

public class PaymentRepresentation {
    private String type;
    private BigDecimal amount;

    /**
     * 销售凭证中的支付信息
     * @param type 付款方式
     * @param amount 付款金额
     */
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

        if (!type.equals(that.type)) return false;
        return amount.compareTo(that.amount) == 0;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }
}
