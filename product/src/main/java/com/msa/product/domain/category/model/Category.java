package com.msa.product.domain.category.model;

import com.msa.product.domain.category.model.vo.CategoryId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(of = "no")
public class Category {

    @EmbeddedId
    private CategoryId no;

    @Getter
    private String name;

    @Getter
    @OneToMany
    List<Category> subCategory = new ArrayList<>();


    public void addSubCategory(Category category) {
        subCategory.add(category);
    }
}
