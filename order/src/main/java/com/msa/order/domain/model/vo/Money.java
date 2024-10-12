package com.msa.order.domain.model.vo;

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
        return createMoney(quantity * getValue());
    }

    protected Money add(Money money) {
        return createMoney(this.getValue() + money.getValue());
    }

    public int getValue() {
        return value;
    }

    public static Money createMoney(int value) {
        return new Money(value);
    }
}
