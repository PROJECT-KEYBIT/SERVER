package com.msa.product.application.inputport;

import com.msa.product.application.outputport.CategoryOutputPort;
import com.msa.product.application.usecase.ClassifyCategoryUsecase;
import com.msa.product.domain.category.model.Category;
import com.msa.product.framework.web.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassifyCategoryInputPort implements ClassifyCategoryUsecase {

    private final CategoryOutputPort outputPort;

    @Override
    public void removeSubCategory(String categoryNo, String subCategoryNo) {
        Category category = outputPort.loadCategory(categoryNo)
                .orElseThrow(() -> new NoSuchElementException(categoryNo + ": 없는 카테고리 아이디입니다."));

        Category subCategory = outputPort.loadCategory(subCategoryNo)
                .orElseThrow(() -> new NoSuchElementException(subCategoryNo + ": 없는 카테고리 아이디입니다."));

        category.removeSubCategory(subCategory);
    }

    @Override
    public List<CategoryDTO> addSubCategory(String categoryNo, String subCategoryNo) {
        Category category = outputPort.loadCategory(categoryNo)
                .orElseThrow(() -> new NoSuchElementException(categoryNo + ": 없는 카테고리 아이디입니다."));

        Category subCategory = outputPort.loadCategory(subCategoryNo)
                .orElseThrow(() -> new NoSuchElementException(categoryNo + ": 없는 카테고리 아이디입니다."));

        category.addSubCategory(subCategory);

        return category.getSubCategory().stream()
                .map(CategoryDTO::mapToDTO)
                .toList();
    }
}
