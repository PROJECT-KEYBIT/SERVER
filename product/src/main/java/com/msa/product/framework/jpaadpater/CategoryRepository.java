package com.msa.product.framework.jpaadpater;

import com.msa.product.domain.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findByTopCategory_No_No(String categoryNo);
}
