package com.msa.order.framework.web.dto.request;

import com.msa.order.framework.web.dto.OrderLineDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CreateOrderRequest {

    private Long userId;
    private String userName;
    private String receiverName;
    private String phoneNumber;
    private String zipcode;
    private String address;
    private List<OrderLineDTO> orderLineDTOList;
}
