package com.msa.order.domain.model.vo;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;

@Embeddable
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

    protected Receiver () {}
    private Receiver(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
