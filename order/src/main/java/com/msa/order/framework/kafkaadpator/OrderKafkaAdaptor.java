package com.msa.order.framework.kafkaadpator;

import com.msa.order.application.port.out.EventOutputPort;
import com.msa.order.domain.event.OrderCanceled;
import com.msa.order.domain.event.OrderCompleted;
import com.msa.order.domain.event.ShippingInfoChanged;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderKafkaAdaptor implements EventOutputPort {


    @Value("${producers.topic.order.cancel}")
    private String TOPIC_ORDER_CANCEL;

    @Value("${producers.topic.order.complete}")
    private String TOPIC_ORDER_COMPLETE;

    @Value("${producers.topic.order.shipping-info.change}")
    private String TOPIC_SHIPPING_INFO_CHANGE;

    private final KafkaTemplate<String, OrderCanceled> orderCanceledKafkaTemplate;
    private final KafkaTemplate<String, OrderCompleted> orderCompleteddKafkaTemplate;
    private final KafkaTemplate<String, ShippingInfoChanged> shippingInfoChangedKafkaTemplate;

    @Override
    @TransactionalEventListener
    public void occurOrderCancelEvent(OrderCanceled orderCanceled) {
        orderCanceledKafkaTemplate.send(TOPIC_ORDER_CANCEL, orderCanceled)
                .thenAccept(result -> log.info("resultOffset: [{}]", result.getRecordMetadata().hasOffset()))
                .exceptionally(throwable -> {
                    log.info("error-message: [{}]", throwable.getMessage());
                    return null;
                });
    }

    @Override
    @TransactionalEventListener
    public void occurOrderCompletedEvent(OrderCompleted orderCompleted) {
        orderCompleteddKafkaTemplate.send(TOPIC_ORDER_COMPLETE, orderCompleted)
                .thenAccept(result -> log.info("resultOffset: [{}]", result.getRecordMetadata().hasOffset()))
                .exceptionally(throwable -> {
                    log.info("error-message: [{}]", throwable.getMessage());
                    return null;
                });
    }

    @Override
    @TransactionalEventListener
    public void occurShippingInfoChangedEvent(ShippingInfoChanged shippingInfoChanged) {
        shippingInfoChangedKafkaTemplate.send(TOPIC_SHIPPING_INFO_CHANGE, shippingInfoChanged)
                .thenAccept(result -> log.info("resultOffset: [{}]", result.getRecordMetadata().hasOffset()))
                .exceptionally(throwable -> {
                    log.info("error-message: [{}]", throwable.getMessage());
                    return null;
                });
    }
}
