package com.msa.product.framework.web.dto;

import com.msa.product.domain.category.model.Category;

public record CategoryDTO (
    String categoryNo,
    String name
) {

    public static CategoryDTO mapToDTO(Category category) {
        return new CategoryDTO(category.getNo(), category.getName());
    }
}
