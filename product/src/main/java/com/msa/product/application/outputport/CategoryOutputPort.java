package com.msa.product.application.outputport;

import com.msa.product.domain.category.model.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryOutputPort {

    Optional<Category> loadCategory(Long categoryId);
    Category save(Category category);
}
