package com.msa.product.application.outputport;

import com.msa.product.domain.category.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryOutputPort {

    Optional<Category> loadCategory(String categoryNo);
    Category save(Category category);

    List<Category> loadSubCategoryById(String categoryNo);
}
