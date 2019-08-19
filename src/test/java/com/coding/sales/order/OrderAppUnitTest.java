package com.coding.sales.order;

import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.member.Member;
import com.coding.sales.member.MemberRepository;
import com.coding.sales.member.MemberType;
import com.coding.sales.order.Discount;
import com.coding.sales.order.MoneyOff;
import com.coding.sales.order.Order;
import com.coding.sales.order.OrderCheckoutException;
import com.coding.sales.order.OrderFactory;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.product.Product;
import com.coding.sales.product.ProductRepository;
import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderAppUnitTest {

    private MemberRepository memberRepository;
    private ProductRepository productRepository;

    public static final String MEMBER_NO = "0001";
    public static final BigDecimal PRODUCT_PRICE = new BigDecimal("10.00");

    @Before
    public void setUp() throws Exception {
        memberRepository = mock(MemberRepository.class);
        when(memberRepository.findById(anyString())).thenReturn(new Member(MEMBER_NO, "", MemberType.NORMAL));

        productRepository = mock(ProductRepository.class);
        when(productRepository.findById(eq("PROD_ID_NORMAL_PRODUCT"))).thenReturn(
                new Product("PROD_ID_NORMAL_PRODUCT", "", PRODUCT_PRICE));

        Product discount90Prod = new Product("PROD_ID_DISCOUNT90", "PROD", PRODUCT_PRICE);
        discount90Prod.addDiscount(Discount.PERCENT_90);
        when(productRepository.findById(eq("PROD_ID_DISCOUNT90"))).thenReturn(discount90Prod);

        Product discount95Prod = new Product("PROD_ID_PERCENT_95", "PROD", PRODUCT_PRICE);
        discount95Prod.addDiscount(Discount.PERCENT_95);
        when(productRepository.findById(eq("PROD_ID_PERCENT_95"))).thenReturn(discount95Prod);

        Product MoneyOffProd = new Product("PROD_ID_MONEY_OFF", "PROD", PRODUCT_PRICE);
        MoneyOffProd.addMoneyOff(MoneyOff.OFF_350_PER_3000);
        when(productRepository.findById(eq("PROD_ID_MONEY_OFF"))).thenReturn(MoneyOffProd);
    }

    @Test
    public void should_support_empty_order() {
        ArrayList<OrderItemCommand> items = null;
        List<String> discounts = null;
        Order order = createOrder(items, "0", discounts);

        order.checkout();

        assertTrue(order.getTotalPrice().compareTo(BigDecimal.ZERO) == 0);
        assertTrue(order.getReceivables().compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    public void should_support_empty_order_print() {
        Order order = createOrder(null, "0", null);
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        assertTrue(representation.getTotalPrice().compareTo(BigDecimal.ZERO) == 0);
        assertTrue(representation.getReceivables().compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    public void should_calc_price_given_an_order() {
        List<OrderItemCommand> items = Arrays.asList(new OrderItemCommand("PROD_ID_NORMAL_PRODUCT", BigDecimal.TEN));
        Order order = createOrder(items, "100", null);
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        validateReceivablesAndTotalDiscount(representation, "100.00", "0.00");
    }

    @Test
    public void should_calc_member_points_for_an_order() {
        List<OrderItemCommand> items = Arrays.asList(new OrderItemCommand("PROD_ID_NORMAL_PRODUCT", BigDecimal.TEN));
        Order order = createOrder(items, "100", null);
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        assertEquals(100, representation.getMemberPointsIncreased());
    }

    @Test
    public void should_increase_member_type() {
        List<OrderItemCommand> items = Arrays.asList(new OrderItemCommand("PROD_ID_NORMAL_PRODUCT", new BigDecimal("1000")));
        Order order = createOrder(items, "10000", null);
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        assertEquals(MemberType.GOLD.toString(), representation.getNewMemberType());
    }

    @Test
    public void should_support_discount_percent_90() {
        List<OrderItemCommand> items = Arrays.asList(new OrderItemCommand("PROD_ID_DISCOUNT90", new BigDecimal("1000")));
        Order order = createOrder(items, "9000", Arrays.asList("9折券"));
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        assertEquals(1, representation.getDiscounts().size());
        validateReceivablesAndTotalDiscount(representation, "9000", "1000");
    }

    @Test
    public void should_support_discount_percent_95() {
        List<OrderItemCommand> items = Arrays.asList(new OrderItemCommand("PROD_ID_PERCENT_95", new BigDecimal("1000")));
        Order order = createOrder(items, "9500", Arrays.asList("95折券"));
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        assertEquals(1, representation.getDiscounts().size());
        validateReceivablesAndTotalDiscount(representation, "9500", "500");
    }

    @Test
    public void should_support_money_off() {
        List<OrderItemCommand> items = Arrays.asList(new OrderItemCommand("PROD_ID_MONEY_OFF", new BigDecimal("1000")));
        Order order = createOrder(items, "8950", null);
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        validateReceivablesAndTotalDiscount(representation, "8950", "1050");
        assertEquals(1, representation.getDiscounts().size());
    }

    @Test(expected = OrderCheckoutException.class)
    public void should_fail_when_checkout_given_payment_not_match_total_price() {
        List<OrderItemCommand> items = Arrays.asList(new OrderItemCommand("PROD_ID_NORMAL_PRODUCT", new BigDecimal("1000")));

        Order order = createOrder(items, "100", null);
        order.checkout();
    }


    private Order createOrder(List<OrderItemCommand> items, String payment, List<String> discounts) {
        String memberId = "0001";
        String createTime = "2019-01-01 10:00:00";
        List<OrderItemCommand> itemCommands = items == null ? new ArrayList<>() : items;
        List<String> discountCommands = discounts == null ? new ArrayList<>() : discounts;

        OrderCommand command = new OrderCommand("ORDER_ID", createTime, memberId,
                itemCommands, getPaymentCommands(payment), discountCommands);

        OrderFactory orderFactory = new OrderFactory(memberRepository, productRepository);

        return orderFactory.createOrder(command);
    }

    private List<PaymentCommand> getPaymentCommands(String money) {
        List<PaymentCommand> payments = new ArrayList<>();
        payments.add(new PaymentCommand("账户余额", new BigDecimal(money)));
        return payments;
    }

    private void validateReceivablesAndTotalDiscount(OrderRepresentation representation, String receivables, String totalDiscount) {
        Offset<BigDecimal> offset = Offset.offset(new BigDecimal("0.001"));

        assertThat(representation.getReceivables()).isCloseTo(new BigDecimal(receivables), offset);
        assertThat(representation.getTotalDiscountPrice()).isCloseTo(new BigDecimal(totalDiscount), offset);
    }
}
