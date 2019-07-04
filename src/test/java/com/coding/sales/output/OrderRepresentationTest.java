package com.coding.sales.output;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderRepresentationTest {

    private Date createTime = new Date();
    private String orderId = "000001";
    private String memberNo = "6236609999";
    private String memberName = "马丁";
    private int memberPointsIncreased = 2;
    private int memberPoints = 10001;
    private String newMemberType = "金卡";
    private BigDecimal totalPrice = BigDecimal.valueOf(404.40);
    private BigDecimal totalDiscountPrice = new BigDecimal("20.0");
    private BigDecimal receivables = new BigDecimal("384.40");
    private String oldMemberType = "普卡";

    @Test
    public void should_print_order_in_correct_format() {
        OrderRepresentation orderRepresentation = new OrderRepresentation(
                orderId, createTime,
                memberNo, memberName, oldMemberType, newMemberType,
                memberPointsIncreased, memberPoints,
                getOrderItems(), totalPrice,
                getDiscountItems(), totalDiscountPrice, receivables, getPayments(), getDiscountCards());
        String actualPrintResult = orderRepresentation.toString();

        String expectedResult = getExpectedTitle() + getExpectedBody();

        Assert.assertEquals(expectedResult, actualPrintResult);
    }

    private List<String> getDiscountCards() {
        return Arrays.asList("9折券");
    }

    private List<OrderItemRepresentation> getOrderItems() {
        return Arrays.asList(
                    new OrderItemRepresentation("0001", "AAA", new BigDecimal("101.1"), new BigDecimal("2"), new BigDecimal(202.2)),
                    new OrderItemRepresentation("0002", "BBB", new BigDecimal("101.1"), new BigDecimal("2"), new BigDecimal(202.2))
            );
    }

    private List<DiscountItemRepresentation> getDiscountItems() {
        return Arrays.asList(
                new DiscountItemRepresentation("0001", "AAA", new BigDecimal(10.0)),
                new DiscountItemRepresentation("0002", "BBB", new BigDecimal(10.0))
        );
    }

    private List<PaymentRepresentation> getPayments() {
        return Arrays.asList(new PaymentRepresentation("账户余额", new BigDecimal(184.4)));
    }

    private String getExpectedTitle() {
        return String.format("方鼎银行贵金属购买凭证\n\n" +
                        "销售单号：%s 日期：%s\n" +
                        "客户卡号：%s 会员姓名：%s 客户等级：%s 累计积分：%d\n",
                orderId, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime),
                memberNo, memberName, newMemberType, memberPoints);
    }

    private String getExpectedBody() {
        return "\n商品及数量           单价         金额\n" +
                "(0001)AAAx2, 101.10, 202.20\n" +
                "(0002)BBBx2, 101.10, 202.20\n" +
                "合计：404.40\n" +
                "\n" +
                "优惠清单：\n" +
                "(0001)AAA: -10.00\n" +
                "(0002)BBB: -10.00\n" +
                "优惠合计：20.00\n" +
                "\n" +
                "应收合计：384.40\n" +
                "收款：\n" +
                " 9折券\n" +
                " 账户余额：184.40\n\n" +
                "客户等级与积分：\n" +
                " 新增积分：2\n" +
                " 恭喜您升级为金卡客户！\n";
    }
}