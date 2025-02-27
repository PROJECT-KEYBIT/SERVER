package com.msa.product.application.service;

import com.msa.product.application.port.in.ClassifyProductUsecase;
import com.msa.product.application.port.out.ProductOutputPort;
import com.msa.product.domain.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassifyProduct implements ClassifyProductUsecase {

    private final ProductOutputPort outputPort;

    @Override
    public List<String> changeCategory(String productNo, List<String> categoryIds) {
        Product product = outputPort.loadProduct(productNo)
                .orElseThrow(() -> new NoSuchElementException(productNo + ": 없는 상품 번호입니다."));

        product.changeCategories(categoryIds);
        return categoryIds;
    }
}
