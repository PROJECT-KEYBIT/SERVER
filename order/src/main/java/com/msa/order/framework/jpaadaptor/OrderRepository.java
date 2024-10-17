package com.msa.order.framework.jpaadaptor;

import com.msa.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByOrderer_OrdererId(Long ordererId);
}
