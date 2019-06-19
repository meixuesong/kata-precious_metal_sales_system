package com.coding.sales.representation;

import java.math.BigDecimal;
import java.util.Objects;

public class DiscountItemRepresentation {
    private String productNo;
    private String productName;
    private BigDecimal discount;

    public DiscountItemRepresentation(String productNo, String productName, BigDecimal discount) {
        this.productNo = productNo;
        this.productName = productName;
        this.discount = discount == null ? BigDecimal.ZERO : discount;
    }

    public String getProductNo() {
        return productNo;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountItemRepresentation that = (DiscountItemRepresentation) o;
        return Objects.equals(productNo, that.productNo) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNo, productName, discount);
    }
}
