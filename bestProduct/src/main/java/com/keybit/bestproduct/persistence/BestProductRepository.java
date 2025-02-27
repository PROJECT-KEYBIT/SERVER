package com.keybit.bestproduct.persistence;

import com.keybit.bestproduct.entity.BestProduct;
import com.keybit.bestproduct.entity.vo.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestProductRepository extends MongoRepository<BestProduct, String> {

    BestProduct findBestProductByItem(Item item);
}
