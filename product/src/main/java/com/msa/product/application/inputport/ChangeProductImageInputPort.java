package com.msa.product.application.inputport;

import com.msa.product.application.outputport.ProductOutputPort;
import com.msa.product.application.usecase.ChangeProductImageUsecase;
import com.msa.product.domain.product.model.Product;
import com.msa.product.domain.product.model.vo.ProductImage;
import com.msa.product.framework.web.dto.request.ChangeProductRequest;
import com.msa.product.framework.web.dto.response.ChangeProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ChangeProductImageInputPort implements ChangeProductImageUsecase {

    private final ProductOutputPort outputPort;

    @Override
    public List<ChangeProductResponse> changeProductImage(String productNo, List<ChangeProductRequest> productImages) {
        Product product = outputPort.loadProduct(productNo)
                .orElseThrow(() -> new NoSuchElementException(productNo + ": 존재하지 않는 상품번호 입니다."));

        List<ProductImage> productImageList = productImages.stream()
                .map(changeProductRequest -> ProductImage.create(changeProductRequest.image(), changeProductRequest.thumbnail()))
                .toList();

        product.changeProductImages(productImageList);

        return productImageList.stream()
                .map(ChangeProductResponse::toMapDTO)
                .toList();
    }
}
