package com.coding.sales;

import com.coding.sales.input.OrderCommand;
import com.coding.sales.output.OrderRepresentation;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderAppUnitTest {

    private MemberRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = mock(MemberRepository.class);
        when(repository.findById(anyString())).thenReturn(new Member("0001", "ABC", MemberType.NORMAL));
    }

    @Test
    public void should_support_empty_order() {
        OrderCommand command = new OrderCommand("0000001",
                "2019-01-01 10:00:00", "0001", null, null,
                null);

        OrderFactory factory = new OrderFactory(repository);
        Order order = factory.createOrder(command);

        assertTrue(order.getTotalPrice().compareTo(BigDecimal.ZERO) == 0);
        assertTrue(order.getReceivables().compareTo(BigDecimal.ZERO) == 0);
    }

    @Test
    public void should_support_empty_order_print() {
        OrderCommand command = new OrderCommand("0000001",
                "2019-01-01 10:00:00", "0001", null, null,
                null);


        OrderFactory factory = new OrderFactory(repository);
        Order order = factory.createOrder(command);

        OrderRepresentation representation = new OrderRepresentation(order);

        assertTrue(representation.getTotalPrice().compareTo(BigDecimal.ZERO) == 0);
        assertTrue(representation.getReceivables().compareTo(BigDecimal.ZERO) == 0);
    }
}
