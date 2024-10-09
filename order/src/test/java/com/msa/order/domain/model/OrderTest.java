package com.msa.order.domain.model;

import com.msa.order.domain.model.vo.*;

import static com.msa.order.domain.model.vo.OrderLinesTest.createOrderLines;

public class OrderTest {

    public static Order createOrder() {
        Orderer orderer = Orderer.createOrderer(1L, "tester1");
        Receiver receiver = Receiver.createReceiver("tester1", "010-0101-0101");
        Address address = Address.createAddress("zipcode", "adress");
        ShippingInfo shippingInfo = ShippingInfo.createShippingInfo(receiver, address);

        OrderLines orderLines = createOrderLines();

        return Order.builder()
                .orderer(orderer)
                .shippingInfo(shippingInfo)
                .orderLines(orderLines)
                .orderStatus(OrderStatus.PREPARING)
                .build();
    }
}