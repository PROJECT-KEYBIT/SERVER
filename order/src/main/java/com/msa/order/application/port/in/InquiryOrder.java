package com.msa.order.application.port.in;

import com.msa.order.framework.web.dto.OrderDTO;

import java.util.List;

public interface InquiryOrder {

    OrderDTO getOrder(String orderNo);
    List<OrderDTO> getAllOrderByOrdererId(Long ordererId);
}
