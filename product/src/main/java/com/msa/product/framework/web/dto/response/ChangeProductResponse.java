package com.msa.product.framework.web.dto.response;

import com.msa.product.domain.product.model.vo.ProductImage;

public record ChangeProductResponse(
        String image,
        boolean thumbnail
) {

    public static ChangeProductResponse toMapDTO(ProductImage productImage) {
        return new ChangeProductResponse(productImage.getImage(), productImage.isThumbnail());
    }
}
