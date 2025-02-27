package com.msa.product.domain.product.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable @Getter
@EqualsAndHashCode(of = "value")
public class Stock {

    @Column(name = "stock")
    private int value;

    public static Stock create(int value) {
        return new Stock(value);
    }

    public Stock add(Stock stock) {
        return create(this.getValue() + stock.getValue());
    }

    public Stock minus(Stock stock) {
        if (this.getValue() > stock.getValue())
            return create(this.getValue() - stock.getValue());
        else
            throw new IllegalArgumentException("차감하려는 재고 갯수가 남아있는 재고 갯수보다 큽니다");
    }

    protected Stock() {}

    private Stock(int value) {
        this.value = value;
    }
}
