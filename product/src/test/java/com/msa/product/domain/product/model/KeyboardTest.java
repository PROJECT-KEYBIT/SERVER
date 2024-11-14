package com.msa.product.domain.product.model;


import com.msa.product.domain.product.model.vo.Description;
import com.msa.product.domain.product.model.vo.Money;
import com.msa.product.domain.product.model.vo.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class KeyboardTest {

    @DisplayName("[Domain][Keyboard] - 키보드 재고 차감 성공 테스트")
    @Test
    void givenMinusRequest_whenMinusStock_thenWorksFine() {
        //given
        String name = "keyboard";
        Description desc = Description.create("desc");
        Money money = Money.create(100);
        Stock stock = Stock.create(1000);
        Product keyboard = Product.create(name, desc, money, stock);

        int minusRequest = 20;

        //when
        keyboard.minusStock(minusRequest);

        //then
        Stock minusRequestStock = Stock.create(minusRequest);
        assertThat(keyboard.getStock()).isEqualTo(stock.minus(minusRequestStock));
    }

    @DisplayName("[Domain][Keyboard] - 키보드 재고 차감 실패 테스트")
    @Test
    void givenMinusRequestLargerThanRemainStock_whenMinusStock_thenThrowError() {
        //given
        String name = "keyboard";
        Description desc = Description.create("desc");
        Money money = Money.create(100);
        Stock stock = Stock.create(10);
        Product keyboard = Product.create(name, desc, money, stock);

        int minusRequest = 20;

        //when, then
        assertThatThrownBy(() -> keyboard.minusStock(minusRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차감하려는 재고 갯수가 남아있는 재고 갯수보다 큽니다");
    }

    @DisplayName("[Domain][Keyboard] - 키보드 애그리거트 생성 테스트")
    @Test
    void createKeyboardTest() {
        //given
        String name = "keyboard";
        Description desc = Description.create("desc");
        Money money = Money.create(100);
        Stock stock = Stock.create(1000);

        //when
        Product keyboard = Product.create(name, desc, money, stock);

        //then
        assertThat(keyboard)
                .hasFieldOrPropertyWithValue("name", "keyboard")
                .hasFieldOrPropertyWithValue("description", desc)
                .hasFieldOrPropertyWithValue("stock", stock);
    }
}