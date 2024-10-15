package com.msa.product.domain.product.model;

import com.msa.product.domain.category.model.vo.CategoryId;
import com.msa.product.domain.product.model.vo.*;
import jakarta.persistence.*;
import lombok.Builder;
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
    private final ProductNo productNo = ProductNo.createProductNo();

    private String name;

    @Embedded
    private Description description;

    @Embedded
    private Money price;

    @Embedded
    private Stock stock;

    @Embedded
    @Builder.Default
    private ProductImages images = ProductImages.empty();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_no"))
    private Set<CategoryId> categories = new HashSet<>();

    protected Product() {}

    public void addCategory(CategoryId categoryId) {
        this.categories.add(categoryId);
    }

    @Builder
    public Product(String name, Description description, Money price, Stock stock, ProductImages images, Set<CategoryId> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.images = images;
        this.categories = categories;
    }
}


