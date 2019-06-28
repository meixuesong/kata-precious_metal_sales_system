package com.coding.sales.output;

import java.math.BigDecimal;

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

        if (!productNo.equals(that.productNo)) return false;
        if (!productName.equals(that.productName)) return false;
        if (price.compareTo(that.price) != 0) return false;
        if (amount.compareTo(that.amount) != 0) return false;
        return subTotal.compareTo(that.subTotal) == 0;


    }

    @Override
    public int hashCode() {
        int result = productNo.hashCode();
        result = 31 * result + productName.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + subTotal.hashCode();
        return result;
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
