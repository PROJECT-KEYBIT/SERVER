package com.msa.product.application.inputport;

import java.util.List;

public interface ClassifyProductUsecase {

    List<String> changeCategory(String productNo, List<String> categoryIds);
}
