package com.msa.order.domain.service;

import com.msa.order.framework.web.dto.request.CreateOrderRequest;
import com.msa.order.framework.web.dto.OrderDTO;

public interface CreateOrder {

    OrderDTO createOrder(CreateOrderRequest createOrderRequest);
}
