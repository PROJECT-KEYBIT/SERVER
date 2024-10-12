package com.msa.order.domain.model.vo;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable
public class OrderLines {

    @ElementCollection
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

    public List<OrderLine> getList() {
        return orderLineList;
    }

    protected OrderLines() {}

    private OrderLines(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }
}
