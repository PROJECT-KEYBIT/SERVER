package com.msa.product.application.inputport;

import com.msa.product.application.outputport.ProductOutputPort;
import com.msa.product.application.usecase.AddCategoryUsecase;
import com.msa.product.domain.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class AddCategoryInputPort implements AddCategoryUsecase {

    private final ProductOutputPort outputPort;

    @Override
    public Long addCategory(String productNo, Long categoryId) {
        Product product = outputPort.loadProduct(productNo)
                .orElseThrow(() -> new NoSuchElementException(productNo + ": 없는 주문 번호입니다."));

        product.addCategory(categoryId);
        return categoryId;
    }
}
