package com.keybit.tag.framework.kafkaadapter.out;

import com.keybit.tag.application.port.out.EventPublisher;
import com.keybit.tag.domain.event.TagRegistered;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagKafkaProducer implements EventPublisher {

    @Value("${producers.topic.tag.register}")
    private String TOPIC_TAG_REGISTERED;

    private final KafkaTemplate<String, Set<TagRegistered>> tagRegisteredKafkaTemplate;

    @Override
    @TransactionalEventListener
    public void occurTagRegisteredEvent(Set<TagRegistered> newTags) {
        tagRegisteredKafkaTemplate.send(TOPIC_TAG_REGISTERED, newTags)
                .thenAccept(result -> log.info("result : [{}]", result));
    }
}
