package com.msa.order.application.port.in;

import com.msa.order.framework.web.dto.request.CreateOrderRequest;
import com.msa.order.framework.web.dto.OrderDTO;

public interface CreateOrder {

    OrderDTO createOrder(CreateOrderRequest createOrderRequest);
}
