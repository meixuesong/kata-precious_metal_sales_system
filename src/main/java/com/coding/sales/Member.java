package com.coding.sales;

import java.math.BigDecimal;

public class Member {
    private String no;
    private String name;
    private int points;
    private MemberType type;

    public Member(String no, String name, MemberType type) {
        this.no = no;
        this.name = name;
        this.type = type;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public MemberType getType() {
        return type;
    }

    int pay(BigDecimal receivables) {
        int increasedPoints = getType().getRate().multiply(new BigDecimal(receivables.intValue())).intValue();
        points += increasedPoints;

        return points;
    }
}
