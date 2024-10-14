package com.msa.order.domain.model.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Product {

    private String productNo;
    private String productName;


    public static Product createProduct(String productNo, String productName) {
        return new Product(productNo, productName);
    }

    protected String getProductNo() {
        return productNo;
    }

    protected String getProductName() {
        return productName;
    }

    protected Product() {}

    private Product(String productNo, String productName) {
        this.productNo = productNo;
        this.productName = productName;
    }
}
