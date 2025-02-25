package com.msa.order.application.service;

import com.msa.order.application.port.out.EventOutputPort;
import com.msa.order.application.port.out.OrderOutputPort;
import com.msa.order.application.port.in.OrderCancel;
import com.msa.order.domain.model.Order;
import com.msa.order.domain.model.vo.OrderNo;
import com.msa.order.domain.model.vo.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderCancelInputPort implements OrderCancel {

    private final OrderOutputPort orderOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public OrderStatus cancel(String orderNo) {
        Order order = orderOutputPort.loadOrder(OrderNo.get(orderNo))
                .orElseThrow(() -> new NoSuchElementException("없는 주문 번호입니다."));

        order.cancel();

        return order.getOrderStatus();
    }
}
