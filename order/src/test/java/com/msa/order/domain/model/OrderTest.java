package com.msa.order.domain.model;

import com.msa.order.domain.model.vo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.msa.order.domain.model.vo.OrderLinesTest.createOrderLines;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderTest {

    @DisplayName("[Domain][Order] - 배송지 정보 변경 성공 테스트")
    @Test
    void givenShippingInfo_whenChangeShippingInfo_thenWordsFine() {
        OrderLines orderLines = createOrderLines(100, 10);
        Order order = createOrder(orderLines);
        order.prepare();

        //given
        Receiver receiver = Receiver.createReceiver("successTester", "010-0101-0102");
        Address address = Address.createAddress("changedZipcode", "changedAddress");
        ShippingInfo shippingInfo = ShippingInfo.createShippingInfo(receiver, address);

        //when
        order.changeShippingInfo(shippingInfo);

        //then
        assertThat(order.getShippingInfo()).isEqualTo(shippingInfo);
    }

    @DisplayName("[Domain][Order] - 배송지 정보 변경 실패 테스트")
    @Test
    void givenShippingInfo_whenChangeShippingInfo_thenThrowAlreadyShippedExceptionError() {
        OrderLines orderLines = createOrderLines(100, 10);
        Order order = createOrder(orderLines);
        order.ship();

        //given
        Receiver receiver = Receiver.createReceiver("successTester", "010-0101-0102");
        Address address = Address.createAddress("changedZipcode", "changedAddress");
        ShippingInfo shippingInfo = ShippingInfo.createShippingInfo(receiver, address);

        //when, then
        assertThatThrownBy(() -> order.changeShippingInfo(shippingInfo))
                .isInstanceOf(AlreadyShippedException.class)
                .hasMessage("이미 배송된 상품입니다.");
    }

    @DisplayName("[Domain][Order] - 주문 취소 성공 테스트")
    @Test
    void whenCancelOrder_thenWordsFine() {
        OrderLines orderLines = createOrderLines(100, 10);
        Order order = createOrder(orderLines);
        order.prepare();

        //when
        order.cancel();

        //then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCELED);
    }

    @DisplayName("[Domain][Order] - 주문 취소 실패 테스트")
    @Test
    void whenCancelOrder_thenThrowAlreadyShippedError() {
        OrderLines orderLines = createOrderLines(100, 10);
        Order order = createOrder(orderLines);
        order.ship();

        //when, then
        assertThatThrownBy(order::cancel)
                .isInstanceOf(AlreadyShippedException.class)
                .hasMessage("이미 배송된 상품입니다.");
    }

    @DisplayName("[Domain][Order] - 총 주문 금액 조회 성공 테스트")
    @Test
    void whenCalculateTotalAmounts_thenReturnCalculatedTotalAmounts() {
        OrderLines orderLines = createOrderLines(100, 10);
        Order order = createOrder(orderLines);

        //when, then
        assertThat(order.calculateTotalAmounts()).isEqualTo(Money.createMoney(1000));
    }

    public static Order createOrder(OrderLines orderLines) {
        Orderer orderer = Orderer.createOrderer(1L, "tester1");
        Receiver receiver = Receiver.createReceiver("tester1", "010-0101-0101");
        Address address = Address.createAddress("zipcode", "adress");
        ShippingInfo shippingInfo = ShippingInfo.createShippingInfo(receiver, address);

        return Order.builder()
                .orderer(orderer)
                .shippingInfo(shippingInfo)
                .orderLines(orderLines)
                .build();
    }
}