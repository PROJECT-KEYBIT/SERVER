package com.msa.product.framework.web.dto.response;

import com.msa.product.domain.category.model.Category;

public record CreateCategoryResponse(
    String categoryNo,
    String name
) {

    public static CreateCategoryResponse mapToDTO(Category category) {
        return new CreateCategoryResponse(category.getNo(), category.getName());
    }
}
