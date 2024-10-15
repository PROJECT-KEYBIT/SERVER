package com.msa.product.domain.product.model.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Description {

    private String description;

    protected Description() {}

    private Description(String description) {
        this.description = description;
    }

    public static Description create(String description) {
        return new Description(description);
    }
}
