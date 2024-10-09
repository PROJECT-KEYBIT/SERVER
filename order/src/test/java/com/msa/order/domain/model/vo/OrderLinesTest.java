package com.msa.order.domain.model.vo;

import java.util.List;

public class OrderLinesTest {

    public static OrderLines createOrderLines() {
        Product product = Product.createProduct("productNo", "productName");
        Money price = Money.createMoney(100);

        OrderLine orderLine = OrderLine.builder()
                .product(product)
                .price(price)
                .quantity(10)
                .build();

        return OrderLines.createOrderLines(List.of(orderLine));
    }
}