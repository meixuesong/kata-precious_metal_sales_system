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
    private List<Discount> discounts;


    public Order(String id, Date createTime, Member member, List<OrderItem> items, List<Discount> discounts) {
        this.id = id;
        this.createTime = createTime;
        this.member = member;
        this.oldMemberType = member.getType();
        this.items = items;
        this.discounts = discounts;
    }

    public void checkout() {
        calcTotalPrice();
        calcDiscountPrice();
        calcReceivables();
        calcMemberPoints();
    }

    private void calcReceivables() {
        receivables = totalPrice.subtract(totalDiscountPrice);
    }

    private void calcDiscountPrice() {
        totalDiscountPrice = BigDecimal.ZERO;
        for (OrderItem item : items) {
            item.calcDiscount(discounts);
            totalDiscountPrice = totalDiscountPrice.add(item.getDiscount());
        }
    }

    private void calcMemberPoints() {
        int oldPoints = member.getPoints();
        member.pay(this.receivables);
        memberPointsIncreased = member.getPoints() - oldPoints;
    }

    private void calcTotalPrice() {
        for (OrderItem item : items) {
            totalPrice = totalPrice.add(item.getSubTotal());
        }
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
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

    public MemberType getNewMemberType() {
        return member.getType();
    }

    public int getMemberPointsIncreased() {
        return memberPointsIncreased;
    }

    public List<OrderItem> getItems() {
        return items;
    }


    public BigDecimal getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

}
