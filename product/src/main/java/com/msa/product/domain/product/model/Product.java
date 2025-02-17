package com.msa.product.domain.product.model;

import com.msa.product.domain.category.model.vo.CategoryNo;
import com.msa.product.domain.common.model.AuditingFields;
import com.msa.product.domain.product.model.vo.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Product {

    @EmbeddedId
    private final ProductNo productNo = ProductNo.createProductNo();

    @Getter
    private String name;

    @Embedded
    private Description description;

    @Embedded
    private Money price;

    @Getter
    @Embedded
    private Stock stock;

    @Getter
    @Embedded
    private ProductImages images = ProductImages.empty();

    @Getter
    @ElementCollection
    @CollectionTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_no"))
    private Set<CategoryNo> categories = new HashSet<>();

    @Getter
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.PREPARING;

    @Getter
    @Embedded
    private AuditingFields auditingFields = new AuditingFields();

    protected Product() {}

    @Builder(builderMethodName = "register")
    private Product(String name, String description, int price, int stock, List<ProductImage> images, String category, String productStatus) {
        this.name = name;
        this.description = Description.create(description);
        this.price = Money.create(price);
        this.stock = Stock.create(stock);
        this.images = ProductImages.create(images);
        this.categories = Set.of(CategoryNo.get(category));
        this.productStatus = ProductStatus.valueOf(productStatus);
    }

    private Product(String name, Description description, Money price, Stock stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public static Product create(String name, Description description, Money price, Stock stock) {
        return new Product(name, description, price, stock);
    }

    public void changeCategories(List<String> categoryNos) {
        Set<CategoryNo> categoryNoList = categoryNos.stream()
                .map(CategoryNo::get)
                .collect(Collectors.toUnmodifiableSet());

        setCategories(categoryNoList);
    }
    public void changeProductImages(List<ProductImage> productImages) {
        getImages().changeProductImageList(productImages);
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

    public String getNo() { return productNo.getNo(); }
    public String getDescription() { return description.getDescription(); }
    public int getPrice() { return price.getValue(); }

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

    private void setStock(Stock stock) {
        this.stock = stock;
    }

    private void setCategories(Set<CategoryNo> categories) {
        this.categories = categories;
    }
}


