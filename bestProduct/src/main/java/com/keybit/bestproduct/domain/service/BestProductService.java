package com.keybit.bestproduct.domain.service;

import com.keybit.bestproduct.domain.model.entity.BestProduct;
import com.keybit.bestproduct.domain.model.entity.Item;
import com.keybit.bestproduct.persistence.BestProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BestProductService {
    private final BestProductRepository bestProductRepository;

    public List<BestProduct> getAllProducts() {
        return bestProductRepository.findAll();
    }

    public Optional<BestProduct> getBookById(String id) {
        return bestProductRepository.findById(id);
    }

    public BestProduct dealBestProduct(Item item) {
        BestProduct bestProduct = bestProductRepository.findBestProductByItem(item);
        if (bestProduct != null) {
            bestProduct.increaseSalesVolume();
        } else {
            bestProduct = BestProduct.register(item);
        }
        return bestProductRepository.save(bestProduct);
    }
}
