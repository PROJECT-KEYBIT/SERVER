package com.keybit.bestproduct.service;

import com.keybit.bestproduct.entity.BestProduct;
import com.keybit.bestproduct.entity.vo.Item;
import com.keybit.bestproduct.persistence.BestProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
            bestProductRepository.save(bestProduct);
        }
        return bestProduct;
    }
}
