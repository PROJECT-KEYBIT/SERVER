package com.msa.order.domain.model.vo;

import lombok.EqualsAndHashCode;
import org.apache.kafka.common.protocol.types.Field;
import org.aspectj.weaver.ast.Or;

@EqualsAndHashCode(of = "ordererId")
public class Orderer {

    private Long ordererId;
    private String ordererName;

    public static Orderer createOrderer(Long ordererId, String ordererName) {
        return new Orderer(ordererId, ordererName);
    }

    private Orderer() {}

    private Orderer(Long ordererId, String ordererName) {
        this.ordererId = ordererId;
        this.ordererName = ordererName;
    }
}
