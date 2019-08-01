package com.coding.sales;

import com.coding.sales.input.OrderCommand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderFactory {
    private MemberRepository memberRepository;

    public OrderFactory(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Order createOrder(OrderCommand command) {

        Member member = memberRepository.findById(command.getMemberId());

        Order order = new Order(command.getOrderId(),
                getCreateTime(command.getCreateTime()), member);

        return order;
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
