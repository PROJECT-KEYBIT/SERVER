package com.msa.order.domain.model.vo;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;

@Getter
@Embeddable
@Access(AccessType.FIELD)
public class ShippingInfo {

    @Embedded
    private Receiver receiver;

    @Embedded
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

    protected ShippingInfo() {}
    private ShippingInfo(Receiver receiver, Address address) {
        this.receiver = receiver;
        this.address = address;
    }
}
