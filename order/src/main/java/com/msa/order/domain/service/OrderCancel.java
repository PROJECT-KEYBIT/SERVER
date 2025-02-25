package com.msa.order.domain.service;

import com.msa.order.domain.model.vo.OrderStatus;

public interface OrderCancel {

    OrderStatus cancel(String orderNo);
}
