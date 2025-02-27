package com.msa.product.framework.web.controller;

import com.msa.product.application.inputport.ClassifyCategoryUsecase;
import com.msa.product.application.inputport.CreateCategoryUsecase;
import com.msa.product.framework.web.dto.CategoryDTO;
import com.msa.product.framework.web.dto.request.CreateCategoryRequest;
import com.msa.product.framework.web.dto.response.CreateCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CreateCategoryUsecase createCategoryUsecase;
    private final ClassifyCategoryUsecase classifyCategoryUsecase;

    @PostMapping("/category")
    public ResponseEntity<CreateCategoryResponse> createCategory(
            @RequestBody CreateCategoryRequest createCategoryRequest
    ) {
        return ResponseEntity.ok(createCategoryUsecase.createCategory(createCategoryRequest));
    }

    @PostMapping("/category/{categoryNo}/subCategory")
    public ResponseEntity<List<CategoryDTO>> addSubCategory(
            @PathVariable String categoryNo,
            @RequestBody CreateCategoryRequest request
    ) {
        return ResponseEntity.ok(classifyCategoryUsecase.addSubCategory(categoryNo, request));
    }

    @DeleteMapping("/category/{categoryNo}/subCategory/{subCategoryNo}")
    public ResponseEntity<List<CategoryDTO>> deleteSubCategory(
            @PathVariable String categoryNo,
            @PathVariable String subCategoryNo
    ) {
        return ResponseEntity.ok(classifyCategoryUsecase.removeSubCategory(categoryNo, subCategoryNo));
    }
}
