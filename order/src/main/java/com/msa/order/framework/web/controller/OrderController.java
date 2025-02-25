package com.msa.order.framework.web.controller;

import com.msa.order.domain.service.ChangeShippingInfo;
import com.msa.order.domain.service.CreateOrder;
import com.msa.order.domain.service.InquiryOrder;
import com.msa.order.domain.service.OrderCancel;
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

    private final ChangeShippingInfo changeShippingInfo;
    private final CreateOrder createOrder;
    private final InquiryOrder inquiryOrder;
    private final OrderCancel orderCancel;

    @PutMapping("/order/{orderNo}/shippingInfo/change")
    public ResponseEntity<ChangeShippingInfoResponse> changeShippingInfo(@PathVariable String orderNo, @RequestBody ChangeShippingInfoRequest shippingInfoRequest) {
        ChangeShippingInfoResponse changeShippingInfoResponse = changeShippingInfo.changeShippingInfo(orderNo, shippingInfoRequest);
        return ResponseEntity.ok(changeShippingInfoResponse);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequest orderRequest) {
        OrderDTO order = createOrder.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/order/{orderNo}/cancel")
    public ResponseEntity<OrderStatus> cancelOrder(@PathVariable String orderNo) {
        OrderStatus cancel = orderCancel.cancel(orderNo);
        return ResponseEntity.ok(cancel);
    }

    @GetMapping("/order/{orderNo}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable String orderNo) {
        OrderDTO order = inquiryOrder.getOrder(orderNo);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/orderer/{ordererId}/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrderByOrdererId(@PathVariable Long ordererId) {
        List<OrderDTO> orderList = inquiryOrder.getAllOrderByOrdererId(ordererId);
        return ResponseEntity.ok(orderList);
    }
}

