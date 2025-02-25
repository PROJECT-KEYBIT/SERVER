package com.msa.order.framework.jpaadaptor;

import com.msa.order.application.port.out.OrderOutputPort;
import com.msa.order.domain.model.Order;
import com.msa.order.domain.model.vo.OrderNo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderJpaAdaptor implements OrderOutputPort {

    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> loadOrder(OrderNo orderNo) {
        return orderRepository.findById(orderNo);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> loadOrdersByOrderer(Long ordererId) {
        return orderRepository.findByOrderer_OrdererId(ordererId);
    }
}
