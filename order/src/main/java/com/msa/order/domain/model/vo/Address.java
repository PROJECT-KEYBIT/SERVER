package com.msa.order.domain.model.vo;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Address {

    private String zipcode;
    private String address;

    protected String getZipcode() {
        return zipcode;
    }

    protected String getAddress() {
        return address;
    }

    public static Address createAddress(String zipcode, String address) {
        return new Address(zipcode, address);
    }

    protected Address() {}
    private Address(String zipcode, String address) {
        this.zipcode = zipcode;
        this.address = address;
    }
}
