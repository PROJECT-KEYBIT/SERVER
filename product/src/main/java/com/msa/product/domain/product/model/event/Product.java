package com.msa.product.domain.product.model.event;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;

@Access(AccessType.FIELD)
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
