package com.msa.product.domain.product.model.vo;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductTags {

    @ElementCollection
    @CollectionTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_no"))
    private Set<ProductTag> productTags = new LinkedHashSet<>();

    public static ProductTags empty() {
        return new ProductTags();
    }
}
