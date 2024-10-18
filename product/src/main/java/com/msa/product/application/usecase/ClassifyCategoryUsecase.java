package com.msa.product.application.usecase;

import com.msa.product.framework.web.dto.CategoryDTO;

import java.util.List;

public interface ClassifyCategoryUsecase {

    void removeSubCategory(String categoryNo, String subCategoryNo);
    List<CategoryDTO> addSubCategory(String categoryNo, String subCategoryNo);
}
