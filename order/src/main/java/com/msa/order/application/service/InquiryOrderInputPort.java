package com.msa.order.application.service;

import com.msa.order.application.port.out.OrderOutputPort;
import com.msa.order.application.port.in.InquiryOrder;
import com.msa.order.domain.model.vo.OrderNo;
import com.msa.order.framework.web.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryOrderInputPort implements InquiryOrder {

    private final OrderOutputPort orderOutputPort;

    @Override
    public OrderDTO getOrder(String orderNo) {
        return orderOutputPort.loadOrder(OrderNo.get(orderNo))
                .map(OrderDTO::mapToDTO)
                .orElseGet(OrderDTO::new);
    }

    @Override
    public List<OrderDTO> getAllOrderByOrdererId(Long ordererId) {
        return orderOutputPort.loadOrdersByOrderer(ordererId).stream()
                .map(OrderDTO::mapToDTO)
                .toList();
    }
}
