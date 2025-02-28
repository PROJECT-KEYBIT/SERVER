package com.keybit.tag.framework.kafkaadaptor.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keybit.tag.application.port.in.ProductTagChangeUsecase;
import com.keybit.tag.domain.event.TagChanged;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagChangeConsumer {
    private final ObjectMapper om = new ObjectMapper();
    private final ProductTagChangeUsecase productTagChangeUsecase;

    @KafkaListener(topics = "${consumers.topic.tag.change}", groupId = "${consumers.topic.tag.group-id}")
    public void consumeTagChange(ConsumerRecord<String, String> record) throws JsonProcessingException {
        System.out.println("tag_changed: " + record.value());
        TagChanged tagChanged = om.readValue(record.value(), TagChanged.class);

        productTagChangeUsecase.productTagChanged(tagChanged);
    }
}
