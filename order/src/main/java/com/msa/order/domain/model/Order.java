package com.msa.order.domain.model;

import com.msa.order.domain.model.event.OrderCanceled;
import com.msa.order.domain.model.event.OrderCompleted;
import com.msa.order.domain.model.event.ShippingInfoChanged;
import com.msa.order.domain.model.vo.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "orders")
public class Order {

    @Getter
    @EmbeddedId
    private OrderNo orderNo;

    @Getter
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
        this.orderStatus = OrderStatus.PAYMENT_WAITING;
    }

    public Money calculateTotalAmounts() {
        return orderLines.totalAmounts();
    }

    public void changeShippingInfo(ShippingInfo shippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(shippingInfo);

        //TODO: 배송지 변경 이벤트!
    }

    public void prepare() {
        setOrderStatus(OrderStatus.PREPARING);
    }

    public void ship() {
        setOrderStatus(OrderStatus.SHIPPED);
    }

    public void delivery() {
        setOrderStatus(OrderStatus.DELIVERING);
    }

    public void deliveryComplete() {
        setOrderStatus(OrderStatus.DELIVERY_COMPLETED);
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

    public String getNo() {
        return this.orderNo.getNo();
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    private void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static OrderCanceled createOrderCanceledEvent(Orderer orderer, OrderLines orderLines) {
        return new OrderCanceled(orderer, orderLines);
    }

    public static OrderCompleted createOrderCompletedEvent(Orderer orderer, OrderLines orderLines) {
        return new OrderCompleted(orderer, orderLines);
    }

    public static ShippingInfoChanged createShippingIngoChangedEvent(OrderNo orderNo, ShippingInfo shippingInfo) {
        return new ShippingInfoChanged(orderNo, shippingInfo);
    }
}
