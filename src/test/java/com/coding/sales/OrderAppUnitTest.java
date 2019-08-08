package com.coding.sales;

import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.output.OrderRepresentation;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderAppUnitTest {

    private MemberRepository memberRepository;
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        memberRepository = mock(MemberRepository.class);
        when(memberRepository.findById(anyString())).thenReturn(new Member("0001", "ABC", MemberType.NORMAL));

        productRepository = mock(ProductRepository.class);
        when(productRepository.findById(anyString())).thenReturn(
                new Product("001", "PROD", new BigDecimal("10.00")));
    }

    @Test
    public void should_support_empty_order() {
        OrderCommand command = new OrderCommand("0000001",
                "2019-01-01 10:00:00", "0001", new ArrayList<OrderItemCommand>(), null,
                null);

        OrderFactory factory = new OrderFactory(memberRepository, productRepository);
        Order order = factory.createOrder(command);
        order.checkout();

        assertTrue(order.getTotalPrice().compareTo(BigDecimal.ZERO) == 0);
        assertTrue(order.getReceivables().compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    public void should_support_empty_order_print() {
        OrderCommand command = new OrderCommand("0000001",
                "2019-01-01 10:00:00", "0001", new ArrayList<OrderItemCommand>(), null,
                null);

        OrderFactory factory = new OrderFactory(memberRepository, productRepository);
        Order order = factory.createOrder(command);
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        assertTrue(representation.getTotalPrice().compareTo(BigDecimal.ZERO) == 0);
        assertTrue(representation.getReceivables().compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    public void should_calc_price_given_an_order() {
        ArrayList<OrderItemCommand> items = new ArrayList<OrderItemCommand>();
        items.add(new OrderItemCommand("001", BigDecimal.TEN));

        OrderCommand command = new OrderCommand("0000001",
                "2019-01-01 10:00:00", "0001",
                items,
                null,
                null);
        OrderFactory factory = new OrderFactory(memberRepository, productRepository);
        Order order = factory.createOrder(command);
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        assertTrue(representation.getTotalPrice().compareTo(new BigDecimal("100.00")) == 0);
        assertTrue(representation.getReceivables().compareTo(new BigDecimal("100.00")) == 0);
    }

    @Test
    public void should_calc_member_points_for_an_order() {
        ArrayList<OrderItemCommand> items = new ArrayList<OrderItemCommand>();
        items.add(new OrderItemCommand("001", BigDecimal.TEN));

        OrderCommand command = new OrderCommand("0000001",
                "2019-01-01 10:00:00", "0001",
                items,
                null,
                null);
        OrderFactory factory = new OrderFactory(memberRepository, productRepository);
        Order order = factory.createOrder(command);
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        assertEquals(100, representation.getMemberPointsIncreased());
    }

    @Test
    public void should_increase_member_type() {
        ArrayList<OrderItemCommand> items = new ArrayList<OrderItemCommand>();
        items.add(new OrderItemCommand("001", new BigDecimal("1000")));

        OrderCommand command = new OrderCommand("0000001",
                "2019-01-01 10:00:00", "0001",
                items,
                null,
                null);
        OrderFactory factory = new OrderFactory(memberRepository, productRepository);
        Order order = factory.createOrder(command);
        order.checkout();

        OrderRepresentation representation = new OrderRepresentation(order);

        assertEquals(MemberType.GOLD.toString(), representation.getNewMemberType());
    }
}
