package com.msa.product.application.inputport;

import com.msa.product.application.outputport.ProductOutputPort;
import com.msa.product.application.usecase.AddStockUsecase;
import com.msa.product.domain.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class AddStockInputPort implements AddStockUsecase {

    private final ProductOutputPort outputPort;

    @Override
    public int addStock(String productNo, int stock) {
        Product product = outputPort.loadProduct(productNo)
                .orElseThrow(() -> new NoSuchElementException(productNo + ": 없는 주문 번호입니다."));

        int newStock = product.addStock(stock);

        outputPort.save(product);
        return newStock;
    }
}
