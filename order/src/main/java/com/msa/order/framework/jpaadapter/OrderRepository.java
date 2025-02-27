package com.msa.order.framework.jpaadapter;

import com.msa.order.domain.model.Order;
import com.msa.order.domain.model.vo.OrderNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderNo> {

    List<Order> findByOrderer_OrdererId(Long ordererId);
}
