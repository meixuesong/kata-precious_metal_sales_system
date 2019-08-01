package com.coding.sales;

import java.math.BigDecimal;

public class OrderItem {
    private Product product;
    private BigDecimal amount;
    private BigDecimal subTotal;

    public OrderItem(Product product, BigDecimal amount) {
        this.product = product;
        this.amount = amount;
        this.subTotal = product.getPrice().multiply(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }
}
