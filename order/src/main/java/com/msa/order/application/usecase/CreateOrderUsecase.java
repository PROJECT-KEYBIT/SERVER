package com.msa.order.application.usecase;

import com.msa.order.framework.web.dto.request.CreateOrderRequest;
import com.msa.order.framework.web.dto.response.CreateOrderResponse;

public interface CreateOrderUsecase {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
