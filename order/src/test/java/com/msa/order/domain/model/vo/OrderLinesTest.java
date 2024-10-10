package com.msa.order.domain.model.vo;

import java.util.List;

public class OrderLinesTest {

    public static OrderLines createOrderLines(int value, int quantity) {
        Product product = Product.createProduct("productNo", "productName");
        Money price = Money.createMoney(value);

        OrderLine orderLine = OrderLine.builder()
                .product(product)
                .price(price)
                .quantity(quantity)
                .build();

        return OrderLines.createOrderLines(List.of(orderLine));
    }
}