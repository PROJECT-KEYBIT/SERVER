package com.keybit.bestproduct.controller;

import com.keybit.bestproduct.dto.ItemDTO;
import com.keybit.bestproduct.dto.ProductDTO;
import com.keybit.bestproduct.entity.BestProduct;
import com.keybit.bestproduct.service.BestProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BestProductController {

    private final BestProductService bestProductService;

    @GetMapping("/best-products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOS = bestProductService.getAllProducts().stream()
                .map(ProductDTO::toMapDTO)
                .toList();

        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/best-product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        ProductDTO productDTO = bestProductService.getBookById(id)
                .map(ProductDTO::toMapDTO)
                .orElseThrow(NoSuchElementException::new);

        return ResponseEntity.ok(productDTO);
    }

    @PostMapping("/best-product")
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ItemDTO itemDTO
    ) {
        BestProduct bestProduct = bestProductService.dealBestProduct(itemDTO.toMapEntity());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductDTO.toMapDTO(bestProduct));
    }
}
