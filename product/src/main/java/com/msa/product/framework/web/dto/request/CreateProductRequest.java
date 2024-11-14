package com.msa.product.framework.web.dto.request;

import com.msa.product.framework.web.dto.ProductImageDTO;

import java.util.List;

public record CreateProductRequest(
    String name,
    String description,
    int price,
    int stock,
    List<ProductImageDTO> images,
    String categoryNo,
    String productStatus
) { }
