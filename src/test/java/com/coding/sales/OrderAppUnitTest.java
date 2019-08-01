package com.coding.sales;

import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.output.OrderRepresentation;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderAppUnitTest {

    private MemberRepository repository;
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        repository = mock(MemberRepository.class);
        when(repository.findById(anyString())).thenReturn(new Member("0001", "ABC", MemberType.NORMAL));

        productRepository = mock(ProductRepository.class);
        when(productRepository.findById(anyString())).thenReturn(
                new Product("001", "PROD", new BigDecimal("10.00")));

    }

    @Test
    public void should_support_empty_order() {
        OrderCommand command = new OrderCommand("0000001",
                "2019-01-01 10:00:00", "0001", new ArrayList<OrderItemCommand>(), null,
                null);

        OrderFactory factory = new OrderFactory(repository, productRepository);
        Order order = factory.createOrder(command);

        assertTrue(order.getTotalPrice().compareTo(BigDecimal.ZERO) == 0);
        assertTrue(order.getReceivables().compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    public void should_support_empty_order_print() {
        OrderCommand command = new OrderCommand("0000001",
                "2019-01-01 10:00:00", "0001", new ArrayList<OrderItemCommand>(), null,
                null);


        OrderFactory factory = new OrderFactory(repository, productRepository);
        Order order = factory.createOrder(command);

        OrderRepresentation representation = new OrderRepresentation(order);

        assertTrue(representation.getTotalPrice().compareTo(BigDecimal.ZERO) == 0);
        assertTrue(representation.getReceivables().compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    public void should_calc_price_given_order() {
        ArrayList<OrderItemCommand> items = new ArrayList<OrderItemCommand>();
        items.add(new OrderItemCommand("001", BigDecimal.TEN));

        OrderCommand command = new OrderCommand("0000001",
                "2019-01-01 10:00:00", "0001",
                items,
                null,
                null);


        OrderFactory factory = new OrderFactory(repository, productRepository);
        Order order = factory.createOrder(command);

        OrderRepresentation representation = new OrderRepresentation(order);

        assertTrue(representation.getTotalPrice().compareTo(new BigDecimal("100.00")) == 0);
        assertTrue(representation.getReceivables().compareTo(new BigDecimal("100.00")) == 0);
    }

}
