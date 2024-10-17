package com.msa.product.framework.jpaadpator;

import com.msa.product.domain.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findByTopCategory_No_No(String categoryNo);
}
