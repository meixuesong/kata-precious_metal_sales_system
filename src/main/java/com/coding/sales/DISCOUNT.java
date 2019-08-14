package com.coding.sales;

import java.math.BigDecimal;

public enum DISCOUNT {
    PERCENT_90("9折券", new BigDecimal("0.9"));

    private String value;
    private BigDecimal discountRate;

    DISCOUNT(String value, BigDecimal discountRate) {
        this.value = value;
        this.discountRate = discountRate;
    }

    public static DISCOUNT from(String value) {
        for (DISCOUNT discount : DISCOUNT.values()) {
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
