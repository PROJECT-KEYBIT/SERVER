package com.msa.product.application.usecase;

import com.msa.product.framework.web.dto.ProductImageDTO;

import java.util.List;

public interface ChangeProductImageUsecase {

    List<ProductImageDTO> changeProductImage(String productNo, List<ProductImageDTO> productImages);
}
