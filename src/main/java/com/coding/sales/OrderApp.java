package com.coding.sales;

import com.coding.sales.command.OrderCommand;
import com.coding.sales.representation.OrderRepresentation;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public OrderRepresentation checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);

        return checkout(command);
    }

    OrderRepresentation checkout(OrderCommand command) {
        OrderRepresentation result = null;

        //TODO: 请完成需求指定的功能

        return result;
    }
}
