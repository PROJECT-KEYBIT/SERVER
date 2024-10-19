package com.msa.product.framework.web.controller;

import com.msa.product.application.usecase.ChangeProductImageUsecase;
import com.msa.product.application.usecase.ChangeStockUsecase;
import com.msa.product.application.usecase.ClassifyProductUsecase;
import com.msa.product.framework.web.dto.request.ChangeProductRequest;
import com.msa.product.framework.web.dto.response.ChangeProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ChangeProductImageUsecase changeProductImageUsecase;
    private final ClassifyProductUsecase classifyProductUsecase;
    private final ChangeStockUsecase changeStockUsecase;

    @PutMapping("/product/{productNo}/product-images")
    public ResponseEntity<List<ChangeProductResponse>> changeProductImage(
            @PathVariable String productNo,
            List<ChangeProductRequest> changeProductRequests
    ) {
        List<ChangeProductResponse> changedProductResponses = changeProductImageUsecase.changeProductImage(productNo, changeProductRequests);
        return ResponseEntity.ok(changedProductResponses);
    }

    @PutMapping("/product/{productNo}/category")
    public ResponseEntity<List<String>> changeCategories(
            @PathVariable String productNo,
            @RequestBody List<String> categoryIds
    ) {
        List<String> changedCategoryIds = classifyProductUsecase.changeCategory(productNo, categoryIds);
        return ResponseEntity.ok(changedCategoryIds);
    }

    @PutMapping("/product/{productNo}/stock")
    public ResponseEntity<Integer> changeStock(
            @PathVariable String productNo,
            int stock
    ) {
        return ResponseEntity.ok(changeStockUsecase.changeStock(productNo, stock));
    }
}
