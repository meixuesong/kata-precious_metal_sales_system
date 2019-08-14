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

    public Member(String no, String name, String type, String points) {
        this.no = no;
        this.name = name;
        this.type = MemberType.from(type);
        this.points = Integer.valueOf(points);
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

    public void pay(BigDecimal money) {
        int increasedPoints = getType().getRate().multiply(new BigDecimal(money.intValue())).intValue();
        points += increasedPoints;

        this.type = MemberType.getMemberType(this.points);
    }

}
