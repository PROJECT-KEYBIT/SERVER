package com.msa.order.domain.model.vo;

public class AlreadyShippedException extends RuntimeException {

    public AlreadyShippedException(String message) {
        super(message);
    }
}
