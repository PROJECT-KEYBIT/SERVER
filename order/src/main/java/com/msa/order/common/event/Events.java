package com.msa.order.common.event;

import com.msa.order.application.port.out.EventOutputPort;
import com.msa.order.domain.event.OrderCanceled;
import com.msa.order.domain.event.OrderCompleted;
import com.msa.order.domain.event.ShippingInfoChanged;

public class Events {

    private static EventOutputPort eventOutputPort;

    public static void setPublisher(EventOutputPort eventOutputPort) {
        Events.eventOutputPort = eventOutputPort;
    }

    public static void publishOrderCancel(OrderCanceled orderCanceled) {
        eventOutputPort.occurOrderCancelEvent(orderCanceled);
    }

    public static void publishShippingInfoChange(ShippingInfoChanged shippingInfoChanged) {
        eventOutputPort.occurShippingInfoChangedEvent(shippingInfoChanged);
    }

    public static void publishOrderComplete(OrderCompleted orderCompleted) {
        eventOutputPort.occurOrderCompletedEvent(orderCompleted);
    }
}
