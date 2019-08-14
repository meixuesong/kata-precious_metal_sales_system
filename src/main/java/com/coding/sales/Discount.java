package com.coding.sales;

import java.math.BigDecimal;

public enum Discount {
    PERCENT_90("9折券", new BigDecimal("0.9")), PERCENT_95("95折券", new BigDecimal("0.95"));

    private String value;
    private BigDecimal discountRate;

    Discount(String value, BigDecimal discountRate) {
        this.value = value;
        this.discountRate = discountRate;
    }

    public static Discount from(String value) {
        for (Discount discount : Discount.values()) {
            if (discount.value.equalsIgnoreCase(value)) {
                return discount;
            }
        }

        throw new IllegalArgumentException("未知的打折券类型");
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    @Override
    public String toString() {
        return value;
    }
}
