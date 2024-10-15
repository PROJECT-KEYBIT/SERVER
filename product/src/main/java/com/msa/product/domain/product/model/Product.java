package com.msa.product.domain.product.model;

import com.msa.product.domain.category.model.vo.CategoryId;
import com.msa.product.domain.product.model.vo.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@SuperBuilder
@DiscriminatorColumn @Getter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

    @EmbeddedId
    private ProductNo productNo;

    private String name;

    @Embedded
    private Description description;

    @Embedded
    private Money price;

    @Embedded
    private Stock stock;

    @Embedded
    private ProductImages images;

    @ElementCollection
    @CollectionTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_no"))
    private Set<CategoryId> categories = new HashSet<>();

    protected Product() {}

    public void addCategory(CategoryId categoryId) {
        this.categories.add(categoryId);
    }
}


