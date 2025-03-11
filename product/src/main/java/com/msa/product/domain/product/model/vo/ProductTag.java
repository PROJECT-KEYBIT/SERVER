package com.msa.product.domain.product.model.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductTag {
    private String tag_no;
    private String name;
}
