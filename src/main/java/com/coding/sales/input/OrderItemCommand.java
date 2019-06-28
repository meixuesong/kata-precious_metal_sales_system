package com.coding.sales.input;

import java.math.BigDecimal;

public class OrderItemCommand {
    String product;
    BigDecimal amount;

    public OrderItemCommand(String product, BigDecimal amount) {
        this.product = product;
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
