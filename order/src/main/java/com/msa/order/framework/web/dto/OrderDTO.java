package com.msa.order.framework.web.dto;

import com.msa.order.domain.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class OrderDTO {

    private String orderNo;
    private String receiverName;
    private String phoneNumber;
    private String zipcode;
    private String address;
    private List<OrderLineDTO> orderLineDTOList;
    private int totalAmounts;

    @Builder
    public OrderDTO(String orderNo, String receiverName, String phoneNumber, String zipcode, String address, List<OrderLineDTO> orderLineDTOList, int totalAmounts) {
        this.orderNo = orderNo;
        this.receiverName = receiverName;
        this.phoneNumber = phoneNumber;
        this.zipcode = zipcode;
        this.address = address;
        this.orderLineDTOList = orderLineDTOList;
        this.totalAmounts = totalAmounts;
    }

    public static OrderDTO mapToDTO(Order order) {

        List<OrderLineDTO> orderLineDTO = order.getOrderLines().getList().stream()
                .map(OrderLineDTO::mapToDTO)
                .toList();

        return OrderDTO.builder()
                .orderNo(order.getOrderNo())
                .receiverName(order.getShippingInfo().getReceiverName())
                .phoneNumber(order.getShippingInfo().getPhoneNumber())
                .zipcode(order.getShippingInfo().getZipcode())
                .address(order.getShippingInfo().getAddress())
                .orderLineDTOList(orderLineDTO)
                .totalAmounts(order.calculateTotalAmounts().getValue())
                .build();
    }
}
