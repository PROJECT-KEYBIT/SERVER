package com.msa.order.application.usecase;

import com.msa.order.framework.web.dto.request.CreateOrderRequest;
import com.msa.order.framework.web.dto.OrderDTO;

public interface CreateOrderUsecase {

    OrderDTO createOrder(CreateOrderRequest createOrderRequest);
}
