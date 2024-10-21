package com.msa.order.domain.model.event;

import com.msa.order.domain.model.vo.OrderLines;
import com.msa.order.domain.model.vo.Orderer;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
public class OrderCanceled implements Serializable {
    @Serial
    private static final long serialVersionUID = 7547697731723631769L;

    private Orderer orderer;
    private OrderLines orderLines;
}
