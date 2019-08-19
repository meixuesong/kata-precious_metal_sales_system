package com.coding.sales.order;

import com.coding.sales.member.Member;
import com.coding.sales.member.MemberType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
    private List<Payment> payments;
    private List<Discount> discounts;


    public Order(String id, Date createTime, Member member, List<OrderItem> items, List<Discount> discounts, List<Payment> payments) {
        this.id = id;
        this.createTime = createTime;
        this.member = member;
        this.oldMemberType = member.getType();
        this.items = items;
        this.discounts = discounts;
        this.payments = payments;
    }

    public void checkout() {
        calcTotalPrice();
        calcPromotion();
        calcTotalDiscountPrice();
        calcReceivables();
        checkPayment();
        calcMemberPoints();
    }

    private void checkPayment() {
        BigDecimal totalPayment = BigDecimal.ZERO;
        for (Payment payment : payments) {
            totalPayment = totalPayment.add(payment.getAmount());
        }

        if (totalPayment.compareTo(receivables) != 0) {
            throw new OrderCheckoutException("付款金额与应收金额不一致。");
        }
    }

    private void calcPromotion() {
        List<Promotion> promotions = getAllPromotions();

        for (Promotion promotion : promotions) {
            for (OrderItem item : items) {
                promotion.calcPromotion(item);
            }
        }
    }

    private List<Promotion> getAllPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        promotions.addAll(discounts);
        promotions.addAll(Arrays.asList(MoneyOff.values()));

        return promotions;
    }

    private void calcReceivables() {
        receivables = totalPrice.subtract(totalDiscountPrice);
    }

    private void calcTotalDiscountPrice() {
        totalDiscountPrice = BigDecimal.ZERO;
        for (OrderItem item : items) {
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

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public List<Payment> getPayments() {
        return payments;
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
