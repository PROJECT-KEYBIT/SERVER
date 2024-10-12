package com.msa.order.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
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
