package com.msa.product.framework.kafkaadpator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.product.application.usecase.ChangeStockUsecase;
import com.msa.product.domain.product.model.event.OrderCanceled;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventConsumers {

    private final ObjectMapper om = new ObjectMapper();
    private final ChangeStockUsecase changeStockUsecase;

    @KafkaListener(topics = "${consumers.topic.order.cancel}", groupId = "${consumers.group-id.name}")
    public void consumerCancel(ConsumerRecord<String, String> record) throws JsonProcessingException {
        System.out.println("order-cancel: " + record.value());
        OrderCanceled orderCanceled = om.readValue(record.value(), OrderCanceled.class);

        orderCanceled.getOrderList().forEach(orderLine -> {
            String productNo = orderLine.getProductNo();
            int orderQuantity = orderLine.getQuantity();
            changeStockUsecase.addStock(productNo, orderQuantity);
        });
    }

}
