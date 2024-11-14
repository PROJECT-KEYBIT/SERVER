package com.msa.product.framework.web.controller;

import com.msa.product.application.usecase.ChangeProductImageUsecase;
import com.msa.product.application.usecase.ChangeStockUsecase;
import com.msa.product.application.usecase.ClassifyProductUsecase;
import com.msa.product.application.usecase.CreateProductUsecase;
import com.msa.product.framework.web.dto.request.ChangeProductRequest;
import com.msa.product.framework.web.dto.request.CreateProductRequest;
import com.msa.product.framework.web.dto.response.ChangeProductResponse;
import com.msa.product.framework.web.dto.response.CreateProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ChangeProductImageUsecase changeProductImageUsecase;
    private final ClassifyProductUsecase classifyProductUsecase;
    private final CreateProductUsecase createProductUsecase;
    private final ChangeStockUsecase changeStockUsecase;

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(
            @RequestBody CreateProductRequest request
    ) {
        CreateProductResponse product = createProductUsecase.createProduct(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @PutMapping("/{productNo}/product-images")
    public ResponseEntity<List<ChangeProductResponse>> changeProductImage(
            @PathVariable String productNo,
            List<ChangeProductRequest> changeProductRequests
    ) {
        List<ChangeProductResponse> changedProductResponses = changeProductImageUsecase.changeProductImage(productNo, changeProductRequests);
        return ResponseEntity.ok(changedProductResponses);
    }

    @PutMapping("/{productNo}/category")
    public ResponseEntity<List<String>> changeCategories(
            @PathVariable String productNo,
            @RequestBody List<String> categoryIds
    ) {
        List<String> changedCategoryIds = classifyProductUsecase.changeCategory(productNo, categoryIds);
        return ResponseEntity.ok(changedCategoryIds);
    }

    @PutMapping("/{productNo}/stock")
    public ResponseEntity<Integer> changeStock(
            @PathVariable String productNo,
            int stock
    ) {
        return ResponseEntity.ok(changeStockUsecase.changeStock(productNo, stock));
    }
}
