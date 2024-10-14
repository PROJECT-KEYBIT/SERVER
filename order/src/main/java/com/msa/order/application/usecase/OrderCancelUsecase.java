package com.msa.order.application.usecase;

import com.msa.order.domain.model.vo.OrderStatus;

public interface OrderCancelUsecase {

    OrderStatus cancel(String orderNo);
}
