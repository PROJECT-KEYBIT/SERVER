package com.msa.product.framework.kafkaadpater.out;

import com.msa.product.application.port.out.TagChangeEventPublisher;
import com.msa.product.domain.product.event.TagChanged;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagChangePublisherTagChange implements TagChangeEventPublisher {

    @Value("${producers.topic.product.tag.change}")
    private String TOPIC_TAG_CHANGED;

    private final KafkaTemplate<String, TagChanged> tagChangedKafkaTemplate;

    @Override
    public void occurTagChangedEvent(TagChanged tagUpdated) {
        tagChangedKafkaTemplate.send(TOPIC_TAG_CHANGED, tagUpdated)
                .thenAccept(result -> {
                    log.info("tagChange-result: [{}]", result.getRecordMetadata().hasOffset());
                })
                .exceptionally(e -> {
                    log.error("tagChange-error: [{}]", e.getMessage());
                    return null;
                });
    }
}
