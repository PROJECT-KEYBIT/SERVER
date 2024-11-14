package com.msa.product.domain.product.model.event;

import lombok.Getter;

@Getter
public class OrderLine {

    private Product product;

    private Money price;

    private int quantity;

    public String getProductNo() {
        return product.getProductNo();
    }

    public String getProductName() {
        return product.getProductName();
    }

    public Money getAmounts() {
        return calculateAmounts();
    }

    protected OrderLine() {}

    private Money calculateAmounts() {
        return price.multiply(quantity);
    }
}
