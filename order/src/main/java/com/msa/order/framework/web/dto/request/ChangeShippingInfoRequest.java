package com.msa.order.framework.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChangeShippingInfoRequest {
    private String orderNo;
    private String receiverName;
    private String phoneNumber;
    private String zipcode;
    private String address;
}
