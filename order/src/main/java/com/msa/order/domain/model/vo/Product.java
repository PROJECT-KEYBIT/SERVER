package com.msa.order.domain.model.vo;

public class Product {

    private String productNo;
    private String productName;


    public static Product createProduct(String productNo, String productName) {
        return new Product(productNo, productName);
    }

    private Product() {}

    private Product(String productNo, String productName) {
        this.productNo = productNo;
        this.productName = productName;
    }
}
