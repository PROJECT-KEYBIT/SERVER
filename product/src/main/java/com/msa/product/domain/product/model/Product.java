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
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

    @EmbeddedId
    private final ProductNo productNo = ProductNo.createProductNo();

    @Getter
    private String name;

    @Getter
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

    @Getter
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.PREPARING;

    protected Product() {}

    public void addCategory(CategoryId categoryId) {
        getCategories().add(categoryId);
    }

    public void addStock(int stock) {
        Stock addRequestStock = Stock.create(stock);
        Stock newAddedStock = getStock().add(addRequestStock);
        setStock(newAddedStock);
    }

    public void minusStock(int stock) {
        Stock minusRequestStock = Stock.create(stock);
        Stock newMinusedStock = getStock().minus(minusRequestStock);
        setStock(newMinusedStock);
    }

    public void preparing() {
        this.status = ProductStatus.PREPARING;
    }

    public void sale() {
        this.status = ProductStatus.SALE;
    }

    private void soldOut() {
        this.status = ProductStatus.SOLD_OUT;
    }

    public void cancel() {
        this.status = ProductStatus.CANCEL;
    }

    public Stock getStock() {
        return stock;
    }

    private void setStock(Stock stock) {
        this.stock = stock;
    }

    public Set<CategoryId> getCategories() {
        return categories;
    }
}


