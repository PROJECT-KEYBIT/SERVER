package com.msa.product.domain.category.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Embeddable @Getter
@EqualsAndHashCode(of = "no")
public class CategoryNo implements Serializable {

    @Serial
    private static final long serialVersionUID = -5113712639704116406L;

    @Column(name = "category_no")
    private String no;

    protected CategoryNo() {}
    private CategoryNo(String no) {
        this.no = no;
    }

    public static CategoryNo get(String no) {
        return new CategoryNo(no);
    }

    public static CategoryNo create() {
        UUID uuid = UUID.randomUUID();
        String year = String.valueOf(LocalDate.now().getYear());
        String no = year + '_' + uuid;

        return new CategoryNo(no);
    }
}
