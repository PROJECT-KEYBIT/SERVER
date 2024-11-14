package com.msa.product.application.usecase;

import com.msa.product.framework.web.dto.request.CreateProductRequest;
import com.msa.product.framework.web.dto.response.CreateProductResponse;

public interface CreateProductUsecase {

    CreateProductResponse createProduct(CreateProductRequest createProductRequest);
}
