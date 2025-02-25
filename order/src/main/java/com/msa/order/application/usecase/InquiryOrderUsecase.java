package com.msa.order.application.usecase;

import com.msa.order.framework.web.dto.OrderDTO;

import java.util.List;

public interface InquiryOrderUsecase {

    OrderDTO getOrder(String orderNo);
    List<OrderDTO> getAllOrderByOrdererId(Long ordererId);
}
