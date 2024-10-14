package com.msa.order.domain.model.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Builder;
import lombok.Getter;

@Embeddable
public class OrderLine {

    @Embedded
    private Product product;

    @Getter
    @Embedded
    private Money price;

    @Getter
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

    @Builder
    private OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    private Money calculateAmounts() {
        return price.multiply(quantity);
    }
}
