package com.msa.product.domain.product.model;

import com.msa.product.domain.category.model.vo.CategoryNo;
import com.msa.product.domain.product.model.vo.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
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

    @Getter
    @Embedded
    @Builder.Default
    private ProductImages images = ProductImages.empty();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_no"))
    private Set<CategoryNo> categories = new HashSet<>();

    @Getter
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.PREPARING;

    protected Product() {}

    public void changeProductImages(List<ProductImage> productImages) {
        getImages().changeProductImageList(productImages);
    }

    public void addCategory(String categoryNo) {
        CategoryNo newCategoryNo = CategoryNo.get(categoryNo);
        getCategories().add(newCategoryNo);
    }

    public void removeCategory(String categoryNo) {
        CategoryNo no = CategoryNo.get(categoryNo);
        getCategories().remove(no);
    }

    public int addStock(int stock) {
        Stock addRequestStock = Stock.create(stock);
        Stock newAddedStock = getStock().add(addRequestStock);
        setStock(newAddedStock);
        return getStock().getValue();
    }

    public int minusStock(int stock) {
        Stock minusRequestStock = Stock.create(stock);
        Stock newMinusedStock = getStock().minus(minusRequestStock);
        setStock(newMinusedStock);
        return getStock().getValue();
    }

    public int changeStock(int stock) {
        Stock changeRequestStock = Stock.create(stock);
        setStock(changeRequestStock);
        return getStock().getValue();
    }

    public void preparing() {
        this.productStatus = ProductStatus.PREPARING;
    }

    public void sale() {
        this.productStatus = ProductStatus.SALE;
    }

    private void soldOut() {
        this.productStatus = ProductStatus.SOLD_OUT;
    }

    public void cancel() {
        this.productStatus = ProductStatus.CANCEL;
    }

    public Stock getStock() {
        return stock;
    }

    private void setStock(Stock stock) {
        this.stock = stock;
    }

    public Set<CategoryNo> getCategories() {
        return categories;
    }
}


