package com.msa.product.framework.jpaadpater;

import com.msa.product.application.port.out.ProductOutputPort;
import com.msa.product.domain.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductOutputPort {

    private final ProductRepository repository;

    @Override
    public Optional<Product> loadProduct(String productNo) {
        return repository.findById(productNo);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }
}
