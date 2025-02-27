package com.msa.product.application.port.in;

import com.msa.product.framework.web.dto.request.CreateCategoryRequest;
import com.msa.product.framework.web.dto.response.CreateCategoryResponse;

public interface CreateCategoryUsecase {

    CreateCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);
}
