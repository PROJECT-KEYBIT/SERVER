package com.msa.order.domain.model;

import com.msa.order.domain.model.vo.*;
import lombok.Builder;

public class Order {

    private OrderNo orderNo;
    private Orderer orderer;
    private ShippingInfo shippingInfo;
    private OrderLines orderLines;
    private OrderStatus orderStatus;

    protected Order() {}

    @Builder
    private Order(Orderer orderer, ShippingInfo shippingInfo, OrderLines orderLines, OrderStatus orderStatus) {
        this.orderNo = OrderNo.createOrderNo();
        this.orderer = orderer;
        this.shippingInfo = shippingInfo;
        this.orderLines = orderLines;
        this.orderStatus = orderStatus;
    }

    public Money totalAmounts() {
        return orderLines.totalAmounts();
    }

    public void changeShippingInfo(ShippingInfo shippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(shippingInfo);

        //TODO: 배송지 변경 이벤트!
    }

    public void cancel() {
        verifyNotYetShipped();
        setOrderStatus(OrderStatus.CANCELED);

        //TODO: 주문 취소 이벤트!
    }

    private void verifyNotYetShipped() {
        if (!orderStatus.isShippingInfoChangeable())
            throw new AlreadyShippedException();
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    private void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
