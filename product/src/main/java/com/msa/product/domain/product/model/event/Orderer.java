package com.msa.product.domain.product.model.event;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "ordererId")
public class Orderer {

    private Long ordererId;
    private String ordererName;

    public static Orderer createOrderer(Long ordererId, String ordererName) {
        return new Orderer(ordererId, ordererName);
    }

    protected Orderer() {}

    private Orderer(Long ordererId, String ordererName) {
        this.ordererId = ordererId;
        this.ordererName = ordererName;
    }
}
