package com.msa.order.framework.web.dto;

import com.msa.order.domain.model.vo.Money;
import com.msa.order.domain.model.vo.OrderLine;
import com.msa.order.domain.model.vo.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderLineDTO {
    private String productId;
    private String productName;
    private int price;
    private int quantity;

    @Builder
    public OrderLineDTO(String productId, String productName, int price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderLine toOrderLine(OrderLineDTO orderLineDTO) {

        Product product = Product.createProduct(
                orderLineDTO.getProductId(),
                orderLineDTO.getProductName());

        return OrderLine.builder()
                .product(product)
                .price(Money.createMoney(orderLineDTO.getPrice()))
                .quantity(orderLineDTO.getQuantity())
                .build();
    }

    public static OrderLineDTO mapToDTO(OrderLine orderLine) {
        return OrderLineDTO.builder()
                .productId(orderLine.getProductNo())
                .productName(orderLine.getProductName())
                .price(orderLine.getPrice().getValue())
                .quantity(orderLine.getQuantity())
                .build();
    }
}
