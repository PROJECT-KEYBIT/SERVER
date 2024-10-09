package com.msa.order.domain.model.vo;

public enum OrderStatus {

    PAYMENT_WAITING() {

        @Override
        public boolean isShippingInfoChangeable() {
            return true;
        }
    },
    PREPARING() {

        @Override
        public boolean isShippingInfoChangeable() {
            return true;
        }
    },
    SHIPPED,
    DELIVERING,
    DELIVERY_COMPLETED,
    CANCELED;

    public boolean isShippingInfoChangeable() {
        return false;
    }
}
