package com.msa.product.domain.model.product.vo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Access(AccessType.FIELD)
public class ProductImages {

    @ElementCollection
    @CollectionTable(name = "product_images")
    private List<ProductImage> productImages = new ArrayList<>();

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
}
