package com.msa.order.domain.model.vo;

import java.util.List;

public class OrderLines {
    private List<OrderLine> orderLineList;

    public Money totalAmounts() {
        return orderLineList.stream()
                .map(OrderLine::getAmounts)
                .reduce((Money::add))
                .get();
    }

    public static OrderLines createOrderLines(List<OrderLine> orderLineList) {
        return new OrderLines(orderLineList);
    }

    private OrderLines() {}

    private OrderLines(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }
}
