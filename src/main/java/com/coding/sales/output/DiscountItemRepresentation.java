package com.coding.sales.output;

import java.math.BigDecimal;

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

        if (!productNo.equals(that.productNo)) return false;
        if (!productName.equals(that.productName)) return false;
        return discount.compareTo(that.discount) == 0;
    }

    @Override
    public int hashCode() {
        int result = productNo.hashCode();
        result = 31 * result + productName.hashCode();
        result = 31 * result + discount.hashCode();
        return result;
    }
}
