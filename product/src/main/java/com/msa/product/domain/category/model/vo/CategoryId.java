package com.msa.product.domain.category.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Embeddable @Getter
@EqualsAndHashCode(of = "value")
public class CategoryId implements Serializable {

    @Serial
    private static final long serialVersionUID = -5113712639704116406L;

    @Column(name = "category_id")
    private Long value;

    protected CategoryId() {}
    private CategoryId(Long value) {
        this.value = value;
    }

    public CategoryId create(Long value) {
        return new CategoryId(value);
    }
}
