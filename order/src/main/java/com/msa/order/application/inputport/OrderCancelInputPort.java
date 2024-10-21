package com.msa.order.application.inputport;

import com.msa.order.application.outputport.EventOutputPort;
import com.msa.order.application.outputport.OrderOutputPort;
import com.msa.order.application.usecase.OrderCancelUsecase;
import com.msa.order.domain.model.Order;
import com.msa.order.domain.model.event.OrderCanceled;
import com.msa.order.domain.model.vo.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderCancelInputPort implements OrderCancelUsecase {

    private final OrderOutputPort orderOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public OrderStatus cancel(String orderNo) {
        Order order = orderOutputPort.loadOrder(orderNo)
                .orElseThrow(() -> new NoSuchElementException("없는 주문 번호입니다."));

        order.cancel();

        OrderCanceled orderCanceledEvent = Order.createOrderCanceledEvent(order.getOrderer(), order.getOrderLines());
        eventOutputPort.occurOrderCancelEvent(orderCanceledEvent);

        return order.getOrderStatus();
    }
}
