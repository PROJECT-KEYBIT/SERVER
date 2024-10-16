package com.msa.product.domain.category.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CategoryTest {


    @DisplayName("[Domain][Category] - 하위 분류 추가 메서드 성공 테스트")
    @Test
    void givenSubCategory_whenAddSubCategory_thenWorksFine() {
        //given
        Category subCategory = Category.create("서브 카테고리");
        Category topCategory = Category.create("상위 카테고리");

        //when
        topCategory.addSubCategory(subCategory);

        //then
        assertThat(topCategory.getSubCategory().contains(subCategory)).isTrue();
    }

    @DisplayName("[Domain][Category] - 하위 분류 제거 메서드 성공 테스트")
    @Test
    void given_when_then() {
        //given
        Category subCategory = Category.create("서브 카테고리");
        Category topCategory = Category.create("상위 카테고리");
        topCategory.addSubCategory(subCategory);

        //when
        topCategory.removeSubCategory(subCategory);

        //then
        assertThat(topCategory.getSubCategory().contains(subCategory)).isFalse();
    }
}