package com.coding.sales;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private Date createTime;
    private Member member;
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private BigDecimal totalDiscountPrice = BigDecimal.ZERO;
    private BigDecimal receivables = BigDecimal.ZERO;
    private MemberType oldMemberType;
    private int memberPointsIncreased;
    private List<OrderItem> items;
    private List<DISCOUNT> discounts;


    public Order(String id, Date createTime, Member member, List<OrderItem> items, List<DISCOUNT> discounts) {
        this.id = id;
        this.createTime = createTime;
        this.member = member;
        this.oldMemberType = member.getType();
        this.items = items;
        this.discounts = discounts;
    }

    public void checkout() {
        calcTotalPrice(this.items);
        this.memberPointsIncreased = this.member.pay(this.receivables);
    }

    private void calcTotalPrice(List<OrderItem> items) {
        for (OrderItem item : items) {
            totalPrice = totalPrice.add(item.getSubTotal());
        }

        receivables = BigDecimal.ZERO;
        totalDiscountPrice = BigDecimal.ZERO;
        for (OrderItem item : items) {
            item.calcDiscount(discounts);
            receivables = receivables.add(item.getReceivables());
            totalDiscountPrice = totalDiscountPrice.add(item.getDiscount());
        }
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
        return member.getType();
    }

    public void setNewMemberType(MemberType newMemberType) {
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


    public BigDecimal getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

}
