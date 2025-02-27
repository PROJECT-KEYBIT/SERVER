package com.msa.product.application.service;

import com.msa.product.application.inputport.ChangeTagUsecase;
import com.msa.product.application.outputport.ProductOutputPort;
import com.msa.product.domain.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ChangeTag implements ChangeTagUsecase {

    private final ProductOutputPort productOutputPort;

    @Override
    public void changeTags(String productNo, String tags) {
        Product product = productOutputPort.loadProduct(productNo)
                .orElseThrow(() -> new NoSuchElementException(productNo + ": 없는 상품 번호 입니다."));

        product.updateTags(tags);
    }
}
