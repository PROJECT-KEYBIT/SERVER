package com.msa.product.framework.web.dto.response;

import com.msa.product.domain.category.model.vo.CategoryNo;
import com.msa.product.domain.product.model.Product;
import com.msa.product.framework.web.dto.ProductImageDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record CreateProductResponse(
    String productNo,
    String name,
    String description,
    int price,
    int stock,
    List<ProductImageDTO> images,
    Set<String> categoryNo,
    String productStatus
) {
    public static CreateProductResponse from(Product product) {
        return new CreateProductResponse(
                product.getNo(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock().getValue(),
                product.getImages().getProductImages().stream()
                        .map(image -> new ProductImageDTO(image.getImage(), image.isThumbnail()))
                        .toList(),
                product.getCategories().stream()
                        .map(CategoryNo::getNo)
                        .collect(Collectors.toUnmodifiableSet()),
                product.getProductStatus().name()
        );
    }
}
