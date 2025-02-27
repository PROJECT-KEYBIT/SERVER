package com.msa.product.application.inputport;

public interface ChangeStockUsecase {

    int changeStock(String productNo, int stock);
    int addStock(String productNo, int stock);
    int minusStock(String productNo, int stock);
}
