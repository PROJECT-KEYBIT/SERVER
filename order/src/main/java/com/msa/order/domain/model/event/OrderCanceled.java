package com.msa.order.domain.model.event;

import com.msa.order.domain.model.vo.OrderLine;
import com.msa.order.domain.model.vo.Orderer;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
public class OrderCanceled implements Serializable {
    @Serial
    private static final long serialVersionUID = 7547697731723631769L;

    private Orderer orderer;
    private List<OrderLine> orderList;
}
