package com.msa.product.domain.category.model;

import com.msa.product.domain.category.model.vo.CategoryNo;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(of = "no")
public class Category {

    @EmbeddedId
    private CategoryNo no;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category topCategory;

    @Getter
    private String name;

    @Getter
    private int depth = 0;

    @Getter
    @OneToMany(mappedBy = "topCategory", cascade = CascadeType.ALL)
    private List<Category> subCategory = new ArrayList<>();

    public static Category create(String name) {
        return new Category(name);
    }

    public String getNo() {
        return this.no.getNo();
    }

    protected Category() {}

    private Category(String name) {
        this.no = CategoryNo.create();
        this.name = name;
    }

    public void addSubCategory(Category category) {
        getSubCategory().add(category);
        category.setTopCategory(this);
    }

    public void removeSubCategory(Category category) {
        getSubCategory().remove(category);
    }

    private void setTopCategory(Category topCategory) {
        this.setDepth(topCategory.getDepth() + 1);
        this.topCategory = topCategory;
    }

    private void setDepth(int depth) {
        this.depth = depth;
    }
}
