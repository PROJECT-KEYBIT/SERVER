package com.msa.order.application.inputport;

import com.msa.order.application.outputport.OrderOutputPort;
import com.msa.order.application.usecase.ChangeShippingInfoUsecase;
import com.msa.order.domain.model.Order;
import com.msa.order.domain.model.vo.Address;
import com.msa.order.domain.model.vo.OrderNo;
import com.msa.order.domain.model.vo.Receiver;
import com.msa.order.domain.model.vo.ShippingInfo;
import com.msa.order.framework.web.dto.request.ChangeShippingInfoRequest;
import com.msa.order.framework.web.dto.response.ChangeShippingInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ChangeShippingInfoInputPort implements ChangeShippingInfoUsecase {

    private final OrderOutputPort orderOutputPort;

    @Override
    public ChangeShippingInfoResponse changeShippingInfo(String orderNo, ChangeShippingInfoRequest changeShippingInfoRequest) {

        Receiver receiver = Receiver.createReceiver(changeShippingInfoRequest.getReceiverName(), changeShippingInfoRequest.getPhoneNumber());
        Address address = Address.createAddress(changeShippingInfoRequest.getZipcode(), changeShippingInfoRequest.getAddress());
        ShippingInfo shippingInfo = ShippingInfo.createShippingInfo(receiver, address);

        Order order = orderOutputPort.loadOrder(OrderNo.get(orderNo))
                .orElseThrow(() -> new NoSuchElementException("없는 주문번호입니다."));

        order.changeShippingInfo(shippingInfo);
        return ChangeShippingInfoResponse.mapToDTO(orderNo, order.getShippingInfo());
    }
}
