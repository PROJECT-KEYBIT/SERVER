package com.msa.product.framework.web.dto.request;



public record ChangeProductRequest(
        String image,
        boolean thumbnail
) {
}
