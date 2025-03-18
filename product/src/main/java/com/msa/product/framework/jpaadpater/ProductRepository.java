package com.msa.product.framework.jpaadpater;

import com.msa.product.domain.product.model.Product;
import com.msa.product.domain.product.model.vo.ProductNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductNo> {
}
