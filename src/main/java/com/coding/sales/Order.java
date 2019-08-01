package com.coding.sales;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private Date createTime;
    private Member member;
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private BigDecimal receivables = BigDecimal.ZERO;
    private MemberType oldMemberType;
    private MemberType newMemberType;
    private int memberPointsIncreased;
    private List<OrderItem> items;

    public Order(String id, Date createTime, Member member, List<OrderItem> items) {
        this.id = id;
        this.createTime = createTime;
        this.member = member;
        this.oldMemberType = member.getType();
        this.newMemberType = member.getType();
        this.items = items;

        for (OrderItem item : items) {
            totalPrice = totalPrice.add(item.getSubTotal());
        }
        receivables = totalPrice;
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

    public List<OrderItem> getItems() {
        return items;
    }
}
