package com.msa.order.application.port.in;

import com.msa.order.domain.model.vo.OrderStatus;

public interface OrderCancel {

    OrderStatus cancel(String orderNo);
}
