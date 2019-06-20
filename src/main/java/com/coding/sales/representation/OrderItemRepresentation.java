package com.coding.sales.representation;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItemRepresentation {
    private String productNo;
    private String productName;
    private BigDecimal price;
    private BigDecimal amount;
    private BigDecimal subTotal;

    /**
     * 销售凭证中的订单行
     * @param productNo 产品编号
     * @param productName 产品名称
     * @param price 单价
     * @param amount 数量
     * @param subTotal 小计
     */
    public OrderItemRepresentation(String productNo, String productName, BigDecimal price, BigDecimal amount, BigDecimal subTotal) {
        this.productNo = productNo;
        this.productName = productName;
        this.price = price == null ? BigDecimal.ZERO : price;
        this.amount = amount == null ? BigDecimal.ZERO : amount;
        this.subTotal = subTotal == null ? BigDecimal.ZERO : subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemRepresentation that = (OrderItemRepresentation) o;
        return Objects.equals(productNo, that.productNo) &&
                Objects.equals(productName, that.productName) &&
                price.compareTo(that.price) == 0 &&
                amount.compareTo(that.amount) == 0 &&
                subTotal.compareTo(that.subTotal) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNo, productName, price, amount, subTotal);
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

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }
}
