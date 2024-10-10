package com.msa.order.framework.web.dto.response;

import com.msa.order.framework.web.dto.OrderLineDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderResponse {

    private String receiverName;
    private String phoneNumber;
    private String zipcode;
    private String address;
    private List<OrderLineDTO> orderLineDTOList;
    private int totalAmounts;
}
