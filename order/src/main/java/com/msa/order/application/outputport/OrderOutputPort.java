package com.msa.order.application.outputport;

import com.msa.order.domain.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderOutputPort {
    Optional<Order> loadOrder(String orderId);
    Order save(Order order);
    List<Order> loadOrdersByOrderer(Long ordererId);
}
