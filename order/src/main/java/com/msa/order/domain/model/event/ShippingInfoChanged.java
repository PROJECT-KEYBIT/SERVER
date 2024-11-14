package com.msa.order.domain.model.event;

import com.msa.order.domain.model.vo.OrderNo;
import com.msa.order.domain.model.vo.ShippingInfo;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
public class ShippingInfoChanged implements Serializable {

    @Serial
    private static final long serialVersionUID = 4150009304599662556L;

    private OrderNo orderNo;
    private ShippingInfo shippingInfo;
}
