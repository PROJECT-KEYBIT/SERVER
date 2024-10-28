package com.msa.product.domain.product.model.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "value")
public class Money {

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
