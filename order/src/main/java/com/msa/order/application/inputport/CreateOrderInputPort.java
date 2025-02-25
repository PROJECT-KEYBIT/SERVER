package com.msa.order.application.inputport;

import com.msa.order.application.outputport.OrderOutputPort;
import com.msa.order.domain.service.CreateOrder;
import com.msa.order.domain.model.Order;
import com.msa.order.domain.model.vo.*;
import com.msa.order.framework.web.dto.OrderLineDTO;
import com.msa.order.framework.web.dto.request.CreateOrderRequest;
import com.msa.order.framework.web.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateOrderInputPort implements CreateOrder {

    private final OrderOutputPort outputPort;

    @Override
    public OrderDTO createOrder(CreateOrderRequest createOrderRequest) {
        Receiver receiver = Receiver.createReceiver(createOrderRequest.getReceiverName(), createOrderRequest.getPhoneNumber());
        Address address = Address.createAddress(createOrderRequest.getZipcode(), createOrderRequest.getAddress());
        ShippingInfo shippingInfo = ShippingInfo.createShippingInfo(receiver, address);

        Orderer orderer = Orderer.createOrderer(createOrderRequest.getUserId(), createOrderRequest.getUserName());

        List<OrderLine> orderLineList = createOrderRequest.getOrderLineDTOList().stream()
                .map(OrderLineDTO::toOrderLine)
                .toList();

        OrderLines orderLines = OrderLines.createOrderLines(orderLineList);

        Order order = Order.builder()
                .orderer(orderer)
                .shippingInfo(shippingInfo)
                .orderLines(orderLines)
                .build();

        return OrderDTO.mapToDTO(outputPort.save(order));
    }
}
