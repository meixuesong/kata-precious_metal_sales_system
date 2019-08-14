package com.coding.sales;

import java.math.BigDecimal;
import java.util.List;

public class OrderItem {
    private Product product;
    private BigDecimal amount;
    private BigDecimal subTotal;
    private BigDecimal discount = BigDecimal.ZERO;

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

    public void calcDiscount(List<Discount> discounts) {
        for (Discount item : discounts) {
            this.discount = calcDiscount(discounts, item);
        }
    }

    private BigDecimal calcDiscount(List<Discount> discounts, Discount discount) {
        if (discounts.contains(discount) && product.getDiscounts().contains(discount)) {
            return product.getPrice().multiply(amount).multiply(
                    BigDecimal.ONE.subtract(discount.getDiscountRate()));
        }

        return BigDecimal.ZERO;
    }

    public BigDecimal getReceivables() {
        return subTotal.subtract(discount);
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }
}
