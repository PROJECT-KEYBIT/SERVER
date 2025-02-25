package com.msa.order.application.inputport;

import com.msa.order.application.outputport.EventOutputPort;
import com.msa.order.application.outputport.OrderOutputPort;
import com.msa.order.application.usecase.OrderCancelUsecase;
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
public class OrderCancelInputPort implements OrderCancelUsecase {

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
