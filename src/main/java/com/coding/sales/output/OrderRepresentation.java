package com.coding.sales.output;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 用于打印的销售凭证
 */
public class OrderRepresentation {
    private String orderId;
    private Date createTime;
    private String memberNo;
    private String memberName;
    private String oldMemberType;
    private String newMemberType;
    private int memberPointsIncreased;
    private int memberPoints;
    private List<OrderItemRepresentation> items;
    private BigDecimal totalPrice;
    private List<DiscountItemRepresentation> discounts;
    private BigDecimal totalDiscountPrice;
    private BigDecimal receivables;
    private List<PaymentRepresentation> payments;
    private List<String> discountCards;
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("0.00");

    /**
     * @param orderId               订单号
     * @param createTime            订单创建时间
     * @param memberNo              会员编号
     * @param memberName            会员姓名
     * @param oldMemberType         原会员等级
     * @param newMemberType         新会员等级。当新老等级不一致时，视为升级
     * @param memberPointsIncreased 本次消费会员新增的积分
     * @param memberPoints          会员最新的积分( = 老积分 + memberPointsIncreased)
     * @param orderItems            订单明细
     * @param totalPrice            订单总金额
     * @param discounts             优惠明细
     * @param totalDiscountPrice    优惠总金额
     * @param receivables           应收金额
     * @param payments              付款记录
     * @param discountCards         付款使用的打折券
     */
    public OrderRepresentation(String orderId, Date createTime,
                               String memberNo, String memberName, String oldMemberType, String newMemberType, int memberPointsIncreased, int memberPoints,
                               List<OrderItemRepresentation> orderItems,
                               BigDecimal totalPrice, List<DiscountItemRepresentation> discounts, BigDecimal totalDiscountPrice,
                               BigDecimal receivables, List<PaymentRepresentation> payments, List<String> discountCards) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.memberNo = memberNo;
        this.memberName = memberName;
        this.oldMemberType = oldMemberType;
        this.newMemberType = newMemberType;
        this.memberPointsIncreased = memberPointsIncreased;
        this.memberPoints = memberPoints;
        this.items = orderItems == null ? new ArrayList<OrderItemRepresentation>() : orderItems;
        this.totalPrice = totalPrice == null ? BigDecimal.ZERO : totalPrice;
        this.discounts = discounts == null ? new ArrayList<DiscountItemRepresentation>() : discounts;
        this.totalDiscountPrice = totalDiscountPrice == null ? BigDecimal.ZERO : totalDiscountPrice;
        this.receivables = receivables == null ? BigDecimal.ZERO : receivables;
        this.payments = payments == null ? new ArrayList<PaymentRepresentation>() : payments;
        this.discountCards = discountCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderRepresentation that = (OrderRepresentation) o;

        if (memberPointsIncreased != that.memberPointsIncreased) return false;
        if (memberPoints != that.memberPoints) return false;
        if (!orderId.equals(that.orderId)) return false;
        if (!createTime.equals(that.createTime)) return false;
        if (!memberNo.equals(that.memberNo)) return false;
        if (!memberName.equals(that.memberName)) return false;
        if (!oldMemberType.equals(that.oldMemberType)) return false;
        if (!newMemberType.equals(that.newMemberType)) return false;
        if (!items.equals(that.items)) return false;
        if (!discounts.equals(that.discounts)) return false;
        if (totalPrice.compareTo(that.totalPrice) != 0) return false;
        if (totalDiscountPrice.compareTo(that.totalDiscountPrice) != 0) return false;
        if (receivables.compareTo(that.receivables) != 0) return false;

        return payments.equals(that.payments);
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + createTime.hashCode();
        result = 31 * result + memberNo.hashCode();
        result = 31 * result + memberName.hashCode();
        result = 31 * result + oldMemberType.hashCode();
        result = 31 * result + newMemberType.hashCode();
        result = 31 * result + memberPointsIncreased;
        result = 31 * result + memberPoints;
        result = 31 * result + items.hashCode();
        result = 31 * result + totalPrice.hashCode();
        result = 31 * result + discounts.hashCode();
        result = 31 * result + totalDiscountPrice.hashCode();
        result = 31 * result + receivables.hashCode();
        result = 31 * result + payments.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getReportTitle() + getOrderDetail() + getDiscount() + getPayment() + getMemberChangeInfo();
    }

    private String getReportTitle() {
        String reportTitle = "方鼎银行贵金属购买凭证\n" +
                "\n" +
                "销售单号：%s 日期：%s\n" +
                "客户卡号：%s 会员姓名：%s 客户等级：%s 累计积分：%d\n" +
                "\n" +
                "商品及数量           单价         金额\n";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return String.format(reportTitle, orderId, dateFormat.format(createTime), memberNo, memberName, newMemberType, memberPoints);
    }

    private String getOrderDetail() {
        StringBuilder result = new StringBuilder();
        for (OrderItemRepresentation item : items) {
            result.append(String.format("(%s)%sx%s, %s, %s\n",
                    item.getProductNo(),
                    item.getProductName(),
                    item.getAmount().toString(),
                    MONEY_FORMAT.format(item.getPrice()),
                    MONEY_FORMAT.format(item.getSubTotal())));
        }
        result.append(String.format("合计：%s\n", MONEY_FORMAT.format(totalPrice)));

        return result.toString();
    }

    private String getDiscount() {
        StringBuilder result = new StringBuilder("\n优惠清单：\n");
        for (DiscountItemRepresentation discount : discounts) {
            result.append(String.format("(%s)%s: -%s\n",
                    discount.getProductNo(),
                    discount.getProductName(),
                    MONEY_FORMAT.format(discount.getDiscount())));
        }
        result.append(String.format("优惠合计：%s\n", MONEY_FORMAT.format(totalDiscountPrice)));

        return result.toString();
    }

    private String getPayment() {
        StringBuilder result = new StringBuilder(String.format("\n应收合计：%s\n收款：\n", MONEY_FORMAT.format(receivables)));

        for (String discountCard : discountCards) {
            result.append(String.format(" %s\n", discountCard));
        }

        for (PaymentRepresentation paymentRepresentation : payments) {
            result.append(String.format(" %s：%s\n",
                    paymentRepresentation.getType(),
                    MONEY_FORMAT.format(paymentRepresentation.getAmount())));
        }

        return result.toString();
    }

    private String getMemberChangeInfo() {
        StringBuilder result = new StringBuilder();
        if (memberNo == null || memberNo.length() <= 0) {
            return "";
        }

        result.append("\n客户等级与积分：\n");
        result.append(String.format(" 新增积分：%d\n", memberPointsIncreased));
        if (!newMemberType.equals(oldMemberType)) {
            result.append(String.format(" 恭喜您升级为%s客户！\n", newMemberType));
        }

        return result.toString();
    }
}
