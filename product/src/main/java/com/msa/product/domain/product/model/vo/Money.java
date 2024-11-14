package com.msa.product.domain.product.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode(of = "value")
public class Money {

    @Column(name = "price")
    private int value;

    protected Money() {}
    private Money(int value) {
        this.value = value;
    }

    protected Money multiply(int quantity) {
        return create(quantity * getValue());
    }

    protected Money add(Money money) {
        return create(this.getValue() + money.getValue());
    }

    public int getValue() {
        return value;
    }

    public static Money create(int value) {
        return new Money(value);
    }
}
