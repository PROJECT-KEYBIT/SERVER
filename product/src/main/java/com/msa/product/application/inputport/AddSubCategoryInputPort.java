package com.msa.product.application.inputport;

import com.msa.product.application.outputport.CategoryOutputPort;
import com.msa.product.application.usecase.AddSubCategoryUsecase;
import com.msa.product.domain.category.model.Category;
import com.msa.product.framework.web.dto.CategoryDTO;
import com.msa.product.framework.web.dto.request.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Transactional
@RequiredArgsConstructor
public class AddSubCategoryInputPort implements AddSubCategoryUsecase {

    private final CategoryOutputPort outputPort;

    @Override
    public List<CategoryDTO> addSubCategory(String categoryNo, CreateCategoryRequest subCategory) {
        Category category = outputPort.loadCategory(categoryNo)
                .orElseThrow(() -> new NoSuchElementException(categoryNo + ": 없는 카테고리 아이디입니다."));

        Category createdSubCategory = Category.create(subCategory.name());

        category.addSubCategory(createdSubCategory);

        return category.getSubCategory().stream()
                .map(CategoryDTO::mapToDTO)
                .toList();
    }
}
