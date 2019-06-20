package com.coding.sales.representation;

import java.math.BigDecimal;
import java.util.Objects;

public class DiscountItemRepresentation {
    private String productNo;
    private String productName;
    private BigDecimal discount;

    /**
     * 销售凭证中的优惠项
     * @param productNo 产品编号
     * @param productName 产品名称
     * @param discount 优惠金额
     */
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
                discount.compareTo(that.discount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNo, productName, discount);
    }
}
