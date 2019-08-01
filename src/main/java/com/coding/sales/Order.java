package com.coding.sales;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String id;
    private Date createTime;
    private Member member;
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private BigDecimal receivables = BigDecimal.ZERO;
    private MemberType oldMemberType;
    private MemberType newMemberType;
    private int memberPointsIncreased;

    public Order(String id, Date createTime, Member member) {
        this.id = id;
        this.createTime = createTime;
        this.member = member;
        this.oldMemberType = member.getType();
        this.newMemberType = member.getType();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Member getMember() {
        return member;
    }

    public BigDecimal getReceivables() {
        return receivables;
    }

    public MemberType getOldMemberType() {
        return oldMemberType;
    }

    public void setOldMemberType(MemberType oldMemberType) {
        this.oldMemberType = oldMemberType;
    }

    public MemberType getNewMemberType() {
        return newMemberType;
    }

    public void setNewMemberType(MemberType newMemberType) {
        this.newMemberType = newMemberType;
    }

    public int getMemberPointsIncreased() {
        return memberPointsIncreased;
    }

    public void setMemberPointsIncreased(int memberPointsIncreased) {
        this.memberPointsIncreased = memberPointsIncreased;
    }
}
