package com.coding.sales;

import java.math.BigDecimal;

public enum MemberType {
    NORMAL("普卡", new BigDecimal("1.0"), 0, 9999),
    GOLD("金卡", new BigDecimal("1.5"), 10000, 49999),
    PLATINUM("白金卡", new BigDecimal("1.8"), 50000, 99999),
    DIAMOND("钻石卡", new BigDecimal("2.0"), 100000, Integer.MAX_VALUE);

    String name;
    private BigDecimal rate;
    int minPoints;
    int maxPoints;

    MemberType(String name, BigDecimal rate, int minPoints, int maxPoints) {
        this.name = name;
        this.rate = rate;
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
    }

    public static MemberType from(String type) {
        for (MemberType value : values()) {
            if (value.name.equalsIgnoreCase(type)) {
                return value;
            }
        }

        throw new IllegalArgumentException("未知的会员类型");
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return name;
    }


    static MemberType getMemberType(int points) {
        for (MemberType value : MemberType.values()) {
            if (points >= value.minPoints && points <= value.maxPoints) {
                return value;
            }
        }

        throw new IllegalArgumentException("积分不在有效范围内");
    }
}
