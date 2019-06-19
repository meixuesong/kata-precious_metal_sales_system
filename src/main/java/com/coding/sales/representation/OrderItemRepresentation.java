package com.coding.sales.representation;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItemRepresentation {
    private String productNo;
    private String productName;
    private BigDecimal price;
    private BigDecimal count;
    private BigDecimal subTotal;

    public OrderItemRepresentation(String productNo, String productName, BigDecimal price, BigDecimal count, BigDecimal subTotal) {
        this.productNo = productNo;
        this.productName = productName;
        this.price = price == null ? BigDecimal.ZERO : price;
        this.count = count == null ? BigDecimal.ZERO : count;
        this.subTotal = subTotal == null ? BigDecimal.ZERO : subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemRepresentation that = (OrderItemRepresentation) o;
        return Objects.equals(productNo, that.productNo) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(price, that.price) &&
                Objects.equals(count, that.count) &&
                Objects.equals(subTotal, that.subTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNo, productName, price, count, subTotal);
    }

    public String getProductNo() {
        return productNo;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getCount() {
        return count;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }
}
