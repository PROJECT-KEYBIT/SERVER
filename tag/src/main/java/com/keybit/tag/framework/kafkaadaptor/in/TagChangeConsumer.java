package com.keybit.tag.framework.kafkaadaptor.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagChangeConsumer {
    private final ObjectMapper om = new ObjectMapper();

    @KafkaListener(topics = "${consumers.topic.tag.change}", groupId = "${consumers.topic.tag.group-id}")
    public void consumeTagChange(ConsumerRecord<String, String> record) {
        //TODO: TagChanged 이벤트를 소모(id 생성 또는 id 보내주기)
    }
}
