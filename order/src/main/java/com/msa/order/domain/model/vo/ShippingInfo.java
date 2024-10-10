package com.msa.order.domain.model.vo;

import lombok.Getter;

@Getter
public class ShippingInfo {

    private Receiver receiver;
    private Address address;

    public static ShippingInfo createShippingInfo(Receiver receiver, Address address) {
        return new ShippingInfo(receiver, address);
    }

    public String getReceiverName() {
        return receiver.getName();
    }

    public String getPhoneNumber() {
        return receiver.getPhoneNumber();
    }

    public String getZipcode() {
        return address.getZipcode();
    }

    public String getAddress() {
        return address.getAddress();
    }

    private ShippingInfo() {}
    private ShippingInfo(Receiver receiver, Address address) {
        this.receiver = receiver;
        this.address = address;
    }
}
