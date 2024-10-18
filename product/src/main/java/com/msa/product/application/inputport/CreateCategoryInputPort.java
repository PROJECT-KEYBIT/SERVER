package com.msa.product.application.inputport;

import com.msa.product.application.outputport.CategoryOutputPort;
import com.msa.product.application.usecase.CreateCategoryUsecase;
import com.msa.product.domain.category.model.Category;
import com.msa.product.framework.web.dto.CategoryDTO;
import com.msa.product.framework.web.dto.request.CreateCategoryRequest;
import com.msa.product.framework.web.dto.response.CreateCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Transactional
@RequiredArgsConstructor
public class CreateCategoryInputPort implements CreateCategoryUsecase {

    private final CategoryOutputPort outputPort;

    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        Category creatdeCategory = Category.create(createCategoryRequest.name());
        Category savedCategory = outputPort.save(creatdeCategory);
        return CreateCategoryResponse.mapToDTO(savedCategory);
    }
}
