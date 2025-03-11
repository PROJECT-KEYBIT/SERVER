package com.keybit.tag.application.service;

import com.keybit.tag.application.port.in.ProductTagChangeUsecase;
import com.keybit.tag.application.port.out.EventPublisher;
import com.keybit.tag.application.port.out.TagOutputPort;
import com.keybit.tag.domain.entity.Tag;
import com.keybit.tag.domain.event.TagChanged;
import com.keybit.tag.domain.event.TagRegistered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductTagChange implements ProductTagChangeUsecase {

    private final TagOutputPort outputPort;
    private final EventPublisher eventPublisher;

    @Override
    public void productTagChanged(TagChanged tagChanged) {
        Set<String> tagNames = tagChanged.tagNames();
        Set<String> existingTags = outputPort.loadTagsByNameIn(tagNames).stream()
                .map(Tag::getName)
                .collect(Collectors.toUnmodifiableSet());

        Set<Tag> newlyRegisteredTags = tagNames.stream()
                .filter(name -> !existingTags.contains(name))
                .map(Tag::register)
                .collect(Collectors.toUnmodifiableSet());

        if (!newlyRegisteredTags.isEmpty()) {
            outputPort.saveAll(newlyRegisteredTags);

            Set<TagRegistered> tags = newlyRegisteredTags.stream()
                    .map(tag -> new TagRegistered(tag.getId(), tag.getName()))
                    .collect(Collectors.toUnmodifiableSet());

            eventPublisher.occurTagRegisteredEvent(tags);
        }
    }
}
