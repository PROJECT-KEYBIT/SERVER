package com.msa.product.application.outputport;

import com.msa.product.domain.product.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductOutputPort {

    Optional<Product> loadProduct(String productNo);
    Product save(Product product);
}
