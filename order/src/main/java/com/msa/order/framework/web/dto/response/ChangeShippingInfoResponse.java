package com.msa.order.framework.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChangeShippingInfoResponse {
    private String receiverName;
    private String phoneNumber;
    private String zipcode;
    private String address;
}
