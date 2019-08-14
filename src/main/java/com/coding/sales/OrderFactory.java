package com.coding.sales;

import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderFactory {
    private MemberRepository memberRepository;
    private ProductRepository productRepository;

    public OrderFactory(MemberRepository memberRepository, ProductRepository productRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(OrderCommand command) {
        Member member = memberRepository.findById(command.getMemberId());

        Order order = new Order(command.getOrderId(),
                getCreateTime(command.getCreateTime()), member,
                getOrderItems(command),
                getDiscounts(command));

        return order;
    }

    private List<DISCOUNT> getDiscounts(OrderCommand command) {
        List<DISCOUNT> discounts = new ArrayList<DISCOUNT>();
        for (String discount : command.getDiscounts()) {
            discounts.add(DISCOUNT.from(discount));
        }
        return discounts;
    }

    private List<OrderItem> getOrderItems(OrderCommand command) {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (OrderItemCommand item : command.getItems()) {
            Product product = productRepository.findById(item.getProduct());
            orderItems.add(new OrderItem(product, item.getAmount()));
        }

        return orderItems;
    }

    private Date getCreateTime(String createTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(createTime);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式不正确");
        }
    }


}
