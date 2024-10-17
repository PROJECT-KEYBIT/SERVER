package com.msa.product.application.usecase;

import com.msa.product.framework.web.dto.request.ChangeProductRequest;
import com.msa.product.framework.web.dto.response.ChangeProductResponse;

import java.util.List;

public interface ChangeProductImageUsecase {

    List<ChangeProductResponse> changeProductImage(String productNo, List<ChangeProductRequest> productImages);
}
