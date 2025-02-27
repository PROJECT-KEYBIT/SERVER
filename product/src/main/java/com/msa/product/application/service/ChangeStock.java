package com.msa.product.application.service;

import com.msa.product.application.inputport.ChangeStockUsecase;
import com.msa.product.application.outputport.ProductOutputPort;
import com.msa.product.domain.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ChangeStock implements ChangeStockUsecase {

    private final ProductOutputPort outputPort;

    @Override
    public int changeStock(String productNo, int stock) {
        Product product = outputPort.loadProduct(productNo)
                .orElseThrow(() -> new NoSuchElementException(productNo + ": 없는 상품 번호입니다."));

        return product.changeStock(stock);
    }

    @Override
    public int addStock(String productNo, int stock) {
        Product product = outputPort.loadProduct(productNo)
                .orElseThrow(() -> new NoSuchElementException(productNo + ": 없는 상품 번호입니다."));

        return product.addStock(stock);
    }

    @Override
    public int minusStock(String productNo, int stock) {
        Product product = outputPort.loadProduct(productNo)
                .orElseThrow(() -> new NoSuchElementException(productNo + ": 없는 상품 번호입니다."));

        return product.minusStock(stock);
    }
}
