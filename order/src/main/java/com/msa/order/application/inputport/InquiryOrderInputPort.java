package com.msa.order.application.inputport;

import com.msa.order.application.outputport.OrderOutputPort;
import com.msa.order.application.usecase.InquiryOrderUsecase;
import com.msa.order.framework.web.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryOrderInputPort implements InquiryOrderUsecase {

    private final OrderOutputPort orderOutputPort;

    @Override
    public OrderDTO getOrder(String orderNo) {
        return orderOutputPort.loadOrder(orderNo)
                .map(OrderDTO::mapToDTO)
                .orElseGet(OrderDTO::new);
    }

    @Override
    public List<OrderDTO> getAllOrderByOrdererId(Long ordererId) {
        return orderOutputPort.loadOrdersByOrderer(ordererId)
                .map(orders -> orders.stream()
                        .map(OrderDTO::mapToDTO)
                        .toList())
                .orElseGet(List::of);
    }
}
