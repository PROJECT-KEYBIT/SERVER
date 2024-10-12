package com.msa.order.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Transient;
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

    @Transient
    private Money amounts;

    public String getProductNo() {
        return product.getProductNo();
    }

    public String getProductName() {
        return product.getProductName();
    }

    protected OrderLine() {}

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
