package com.msa.order.domain.model;

import com.msa.order.domain.model.vo.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
public class Order {

    @EmbeddedId
    private OrderNo orderNo;

    @Embedded
    private Orderer orderer;

    @Getter
    @Embedded
    private ShippingInfo shippingInfo;

    @Getter
    @Embedded
    private OrderLines orderLines;

    @Getter
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    protected Order() {}

    @Builder
    private Order(Orderer orderer, ShippingInfo shippingInfo, OrderLines orderLines) {
        this.orderNo = OrderNo.createOrderNo();
        this.orderer = orderer;
        this.shippingInfo = shippingInfo;
        this.orderLines = orderLines;
        this.orderStatus = OrderStatus.PREPARING;
    }

    public Money calculateTotalAmounts() {
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
            throw new AlreadyShippedException("이미 배송된 상품입니다.");
    }

    public String getOrderNo() {
        return this.orderNo.getNo();
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    private void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
