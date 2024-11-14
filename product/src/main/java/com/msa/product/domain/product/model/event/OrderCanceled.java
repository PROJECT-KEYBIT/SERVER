package com.msa.product.domain.product.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCanceled implements Serializable {
    @Serial
    private static final long serialVersionUID = 7547697731723631769L;

    private Orderer orderer;
    private List<OrderLine> orderList;
}
