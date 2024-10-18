package com.msa.product.application.usecase;

import com.msa.product.framework.web.dto.CategoryDTO;
import com.msa.product.framework.web.dto.request.CreateCategoryRequest;
import com.msa.product.framework.web.dto.response.CreateCategoryResponse;

import java.util.List;

public interface CreateCategoryUsecase {

    CreateCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);
}
