package com.msa.order.framework.web.dto.response;

import com.msa.order.domain.model.Order;
import com.msa.order.framework.web.dto.OrderLineDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CreateOrderResponse {

    private String receiverName;
    private String phoneNumber;
    private String zipcode;
    private String address;
    private List<OrderLineDTO> orderLineDTOList;
    private int totalAmounts;

    @Builder
    public CreateOrderResponse(String receiverName, String phoneNumber, String zipcode, String address, List<OrderLineDTO> orderLineDTOList, int totalAmounts) {
        this.receiverName = receiverName;
        this.phoneNumber = phoneNumber;
        this.zipcode = zipcode;
        this.address = address;
        this.orderLineDTOList = orderLineDTOList;
        this.totalAmounts = totalAmounts;
    }

    public static CreateOrderResponse mapToDTO(Order order) {

        List<OrderLineDTO> orderLineDTO = order.getOrderLines().getList().stream()
                .map(OrderLineDTO::mapToDTO)
                .toList();

        return CreateOrderResponse.builder()
                .receiverName(order.getShippingInfo().getReceiverName())
                .phoneNumber(order.getShippingInfo().getPhoneNumber())
                .zipcode(order.getShippingInfo().getZipcode())
                .address(order.getShippingInfo().getAddress())
                .orderLineDTOList(orderLineDTO)
                .totalAmounts(order.calculateTotalAmounts().getValue())
                .build();
    }
}
