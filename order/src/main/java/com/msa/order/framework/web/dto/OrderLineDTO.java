package com.msa.order.framework.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderLineDTO {
    private String productId;
    private String productName;
    private int money;
    private int quantity;
}
