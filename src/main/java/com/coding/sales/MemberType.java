package com.coding.sales;

import java.math.BigDecimal;

public enum MemberType {
    NORMAL("普卡", new BigDecimal("1.0")),
    GOLD("金卡", new BigDecimal("1.5")),
    PLATINUM("白金", new BigDecimal("1.8")),
    DIAMOND("钻石", new BigDecimal("2.0"));

    String name;
    private BigDecimal rate;

    MemberType(String name, BigDecimal rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return name;
    }


}
