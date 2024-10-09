package com.msa.order.domain.model.vo;

public class Address {

    private String zipcode;
    private String address;


    public static Address createAddress(String zipcode, String address) {
        return new Address(zipcode, address);
    }

    private Address() {}
    private Address(String zipcode, String address) {
        this.zipcode = zipcode;
        this.address = address;
    }
}
