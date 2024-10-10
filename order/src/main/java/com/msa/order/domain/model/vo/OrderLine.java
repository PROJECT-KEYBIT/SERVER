package com.msa.order.domain.model.vo;

import lombok.Builder;
import lombok.Getter;

public class OrderLine {

    private Product product;
    @Getter private Money price;
    @Getter private int quantity;
    private Money amounts;

    public String getProductNo() {
        return product.getProductNo();
    }

    public String getProductName() {
        return product.getProductName();
    }

    private OrderLine() {}

    @Builder
    private OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() {
        return price.multiply(quantity);
    }

    public Money getAmounts() {
        return amounts;
    }
}
