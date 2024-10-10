package com.msa.order.domain.model.vo;

public class Receiver {
    private String name;
    private String phoneNumber;


    public static Receiver createReceiver(String name, String phoneNumber) {
        return new Receiver(name, phoneNumber);
    }

    protected String getName() {
        return this.name;
    }

    protected String getPhoneNumber() {
        return this.phoneNumber;
    }

    private Receiver () {}
    private Receiver(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
