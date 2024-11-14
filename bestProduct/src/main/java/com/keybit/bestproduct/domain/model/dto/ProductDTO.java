package com.keybit.bestproduct.domain.model.dto;

import com.keybit.bestproduct.domain.model.entity.BestProduct;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {

    private String id;
    private String title;
    private long salesVolume;

    public static ProductDTO toMapDTO(BestProduct product) {
        return new ProductDTO(product.getId(),
                              product.getItem().getTitle(),
                              product.getSalesVolume());
    }

    private ProductDTO(String id, String title, long salesVolume) {
        this.id = id;
        this.title = title;
        this.salesVolume = salesVolume;
    }
}
