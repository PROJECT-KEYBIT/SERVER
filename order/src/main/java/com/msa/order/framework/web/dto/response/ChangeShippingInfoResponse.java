package com.msa.order.framework.web.dto.response;

import com.msa.order.domain.model.vo.ShippingInfo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChangeShippingInfoResponse {

    private String orderNo;
    private String receiverName;
    private String phoneNumber;
    private String zipcode;
    private String address;

    public static ChangeShippingInfoResponse mapToDTO(String orderNo, ShippingInfo shippingInfo) {
        return new ChangeShippingInfoResponse(orderNo, shippingInfo.getReceiverName(), shippingInfo.getPhoneNumber(), shippingInfo.getZipcode(), shippingInfo.getAddress());
    }

    private ChangeShippingInfoResponse(String orderNo, String receiverName, String phoneNumber, String zipcode, String address) {
        this.orderNo = orderNo;
        this.receiverName = receiverName;
        this.phoneNumber = phoneNumber;
        this.zipcode = zipcode;
        this.address = address;
    }
}
