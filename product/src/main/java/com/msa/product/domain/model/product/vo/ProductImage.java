package com.msa.product.domain.model.product.vo;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Embeddable
public class ProductImage {

    private String image;
    private boolean thumbnail;

    public static ProductImage create(String image, boolean represent) {
        return new ProductImage(image, represent);
    }

    protected ProductImage() {}
    private ProductImage(String image, boolean thumbnail) {
        this.image = image;
        this.thumbnail = thumbnail;
    }
}
