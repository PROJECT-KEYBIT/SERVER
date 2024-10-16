package com.msa.order.framework.jpaadaptor;

import com.msa.order.domain.model.Order;
import com.msa.order.domain.model.vo.OrderNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderNo> {

    List<Order> findByOrderer_OrdererId(@Param("ordererId") Long ordererId);
    Optional<Order> findByOrderNo_No(@Param("orderId") String orderNo);
}
