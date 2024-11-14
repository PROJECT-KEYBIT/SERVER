package com.msa.product.application.inputport;

import com.msa.product.application.outputport.ProductOutputPort;
import com.msa.product.application.usecase.CreateProductUsecase;
import com.msa.product.domain.product.model.Product;
import com.msa.product.domain.product.model.vo.ProductImage;
import com.msa.product.framework.web.dto.request.CreateProductRequest;
import com.msa.product.framework.web.dto.response.CreateProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateProductInputPort implements CreateProductUsecase {

    private final ProductOutputPort outputPort;

    @Override
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {

        Product newProduct = Product.register()
                .name(createProductRequest.name())
                .description(createProductRequest.description())
                .price(createProductRequest.price())
                .stock(createProductRequest.stock())
                .images(createProductRequest.images().stream()
                        .map(image -> ProductImage.create(image.image(), image.represent()))
                        .toList())
                .productStatus(createProductRequest.productStatus())
                .build();

        outputPort.save(newProduct);

        return CreateProductResponse.from(newProduct);
    }
}
