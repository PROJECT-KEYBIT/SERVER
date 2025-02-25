package com.msa.order.domain.event;

import com.msa.order.domain.model.vo.OrderLines;
import com.msa.order.domain.model.vo.Orderer;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
public class OrderCompleted implements Serializable {
    @Serial
    private static final long serialVersionUID = 1856566104204497185L;

    private Orderer orderer;
    private OrderLines orderLines;
}
