package com.msa.product.application.usecase;

public interface ClassifyProductUsecase {

    String addCategory(String productNo, String categoryNo);
    void removeCategory(String productNo, String categoryNo);
}
