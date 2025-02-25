package com.msa.order.application.port.out;

import com.msa.order.domain.model.Order;
import com.msa.order.domain.model.vo.OrderNo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderOutputPort {
    Optional<Order> loadOrder(OrderNo orderNo);
    Order save(Order order);
    List<Order> loadOrdersByOrderer(Long ordererId);
}
