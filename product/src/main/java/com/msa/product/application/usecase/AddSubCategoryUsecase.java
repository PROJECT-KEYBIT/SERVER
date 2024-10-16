package com.msa.product.application.usecase;

import com.msa.product.framework.web.dto.CategoryDTO;
import com.msa.product.framework.web.dto.request.CreateCategoryRequest;

import java.util.List;

public interface AddSubCategoryUsecase {

    List<CategoryDTO> addSubCategory(String categoryNo, CreateCategoryRequest subCategory);
}
