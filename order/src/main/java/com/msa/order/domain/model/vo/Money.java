package com.msa.order.domain.model.vo;

public class Money {

    private int value;

    private Money() {}
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
