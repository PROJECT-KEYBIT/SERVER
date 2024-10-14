package com.msa.order.framework.web.controller;

import com.msa.order.application.usecase.ChangeShippingInfoUsecase;
import com.msa.order.application.usecase.CreateOrderUsecase;
import com.msa.order.application.usecase.InquiryOrderUsecase;
import com.msa.order.application.usecase.OrderCancelUsecase;
import com.msa.order.domain.model.vo.OrderStatus;
import com.msa.order.framework.web.dto.OrderDTO;
import com.msa.order.framework.web.dto.request.ChangeShippingInfoRequest;
import com.msa.order.framework.web.dto.request.CreateOrderRequest;
import com.msa.order.framework.web.dto.response.ChangeShippingInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final ChangeShippingInfoUsecase changeShippingInfoUsecase;
    private final CreateOrderUsecase createOrderUsecase;
    private final InquiryOrderUsecase inquiryOrderUsecase;
    private final OrderCancelUsecase orderCancelUsecase;

    @PutMapping("/order/{orderNo}/shippingInfo/change")
    public ResponseEntity<ChangeShippingInfoResponse> changeShippingInfo(@PathVariable String orderNo, @RequestBody ChangeShippingInfoRequest shippingInfoRequest) {
        ChangeShippingInfoResponse changeShippingInfoResponse = changeShippingInfoUsecase.changeShippingInfo(orderNo, shippingInfoRequest);
        return ResponseEntity.ok(changeShippingInfoResponse);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequest orderRequest) {
        OrderDTO order = createOrderUsecase.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/order/{orderNo}/cancel")
    public ResponseEntity<OrderStatus> cancelOrder(@PathVariable String orderNo) {
        OrderStatus cancel = orderCancelUsecase.cancel(orderNo);
        return ResponseEntity.ok(cancel);
    }

    @GetMapping("/order/{orderNo}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable String orderNo) {
        OrderDTO order = inquiryOrderUsecase.getOrder(orderNo);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/orderer/{ordererId}/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrderByOrdererId(@PathVariable Long ordererId) {
        List<OrderDTO> orderList = inquiryOrderUsecase.getAllOrderByOrdererId(ordererId);
        return ResponseEntity.ok(orderList);
    }
}

