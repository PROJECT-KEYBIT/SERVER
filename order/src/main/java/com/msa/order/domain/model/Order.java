package com.msa.order.domain.model;

import com.msa.order.common.event.Events;
import com.msa.order.domain.event.OrderCanceled;
import com.msa.order.domain.event.OrderCompleted;
import com.msa.order.domain.event.ShippingInfoChanged;
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

        Events.publishShippingInfoChange(
                new ShippingInfoChanged(getOrderNo(), getShippingInfo())
        );
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

        Events.publishOrderCancel(
                new OrderCanceled(getOrderer(), getOrderLines().getList())
        );
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

    public static OrderCompleted createOrderCompletedEvent(Orderer orderer, OrderLines orderLines) {
        return new OrderCompleted(orderer, orderLines);
    }
}
