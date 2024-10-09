package com.msa.order.domain.model.vo;

public class ShippingInfo {

    private Receiver receiver;
    private Address address;

    public static ShippingInfo createShippingInfo(Receiver receiver, Address address) {
        return new ShippingInfo(receiver, address);
    }

    private ShippingInfo() {}
    private ShippingInfo(Receiver receiver, Address address) {
        this.receiver = receiver;
        this.address = address;
    }
}
