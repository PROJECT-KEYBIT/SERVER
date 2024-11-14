package com.msa.product.domain.product.model.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode(of = "no")
public class ProductNo implements Serializable {

    @Serial
    private static final long serialVersionUID = 7435827638264974055L;

    @Getter
    private String no;

    protected ProductNo() {}

    private ProductNo(String no) {
        this.no = no;
    }

    public static ProductNo createProductNo() {
        UUID uuid = UUID.randomUUID();
        String year = String.valueOf(LocalDate.now().getYear());
        String no = year + '_' + uuid;

        return new ProductNo(no);
    }
}
