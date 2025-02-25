package com.msa.order.application.port.out;

import com.msa.order.domain.event.OrderCanceled;
import com.msa.order.domain.event.OrderCompleted;
import com.msa.order.domain.event.ShippingInfoChanged;

public interface EventOutputPort {

    void occurOrderCancelEvent(OrderCanceled orderCanceled);
    void occurOrderCompletedEvent(OrderCompleted orderCompleted);
    void occurShippingInfoChangedEvent(ShippingInfoChanged shippingInfoChanged);
}
