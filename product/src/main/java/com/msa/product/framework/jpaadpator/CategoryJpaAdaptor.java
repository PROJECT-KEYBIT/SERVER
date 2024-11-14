package com.msa.product.framework.jpaadpator;

import com.msa.product.application.outputport.CategoryOutputPort;
import com.msa.product.domain.category.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryJpaAdaptor implements CategoryOutputPort {

    private final CategoryRepository repository;

    @Override
    public Optional<Category> loadCategory(String categoryNo) {
        return repository.findById(categoryNo);
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public List<Category> loadSubCategoryById(String categoryNo) {
        return repository.findByTopCategory_No_No(categoryNo);
    }
}
