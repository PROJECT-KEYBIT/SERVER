package com.msa.product.domain.product.model.vo;


import com.msa.product.domain.product.pattern.TagPattern;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;

import static com.msa.product.domain.product.pattern.TagPattern.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductTags {

    private String tags;

    @ElementCollection
    @CollectionTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_no"))
    private Set<String> tagIds = new LinkedHashSet<>();

    public static ProductTags empty() {
        return new ProductTags();
    }

    public void updateTags(String tags) {
        this.tags = tags;
    }
}
