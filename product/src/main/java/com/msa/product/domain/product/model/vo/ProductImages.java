package com.msa.product.domain.product.model.vo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Access(AccessType.FIELD)
public class ProductImages {

    @ElementCollection
    @CollectionTable(name = "product_images")
    private List<ProductImage> productImages = new ArrayList<>();

    protected ProductImages() {}

    private ProductImages(List<ProductImage> productImages) {
        long count = productImages.stream()
                .filter(ProductImage::isThumbnail)
                .count();

        checkIfRepresentImageIsLargerLimitCount(count);

        this.productImages = productImages;
    }

    private void checkIfRepresentImageIsLargerLimitCount(long count) {
        int REPRESENT_IMAGE_LIMIT_COUNT = 1;
        if (count > REPRESENT_IMAGE_LIMIT_COUNT)
            throw new IllegalArgumentException("대표 이미지 개수 " + count + "가 허용 개수 " + REPRESENT_IMAGE_LIMIT_COUNT + " 보다 큽니다.");
    }

    public static ProductImages empty() {
        return new ProductImages();
    }

    public static ProductImages create(List<ProductImage> productImages) {
        return new ProductImages(productImages);
    }

    public List<ProductImage> changeProductImageList(List<ProductImage> productImages) {
        getProductImages().clear();
        setProductImages(productImages);

        return getProductImages();
    }

    public boolean hasThumbnail() {
        for (ProductImage image : productImages)
            if (image.isThumbnail()) return true;

        return false;
    }

    public ProductImage getThumbnail() {
        return productImages.stream()
                .filter(ProductImage::isThumbnail)
                .findFirst()
                .orElse(ProductImage.create("DEFAULT_IMAGE", false));
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    private void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }
}
