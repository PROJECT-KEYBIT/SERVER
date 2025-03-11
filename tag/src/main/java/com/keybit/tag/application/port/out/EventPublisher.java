package com.keybit.tag.application.port.out;

import com.keybit.tag.domain.event.TagRegistered;

import java.util.Set;

public interface EventPublisher {

    void occurTagRegisteredEvent(Set<TagRegistered> newTags);
}
