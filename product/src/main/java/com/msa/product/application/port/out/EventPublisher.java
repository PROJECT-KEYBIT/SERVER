package com.msa.product.application.port.out;

import com.msa.product.domain.product.event.TagChanged;

public interface EventPublisher {
    void occurTagChangedEvent(TagChanged tagUpdated);
}
