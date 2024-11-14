package com.msa.product.application.usecase;

import java.util.List;

public interface ClassifyProductUsecase {

    List<String> changeCategory(String productNo, List<String> categoryIds);
}
