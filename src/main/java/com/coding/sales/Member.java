package com.coding.sales;

public class Member {
    private final String id;
    private String no;
    private String name;
    private int points;
    private MemberType type;

    public Member(String id, String name, MemberType type) {
        this.id = id;
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
}
