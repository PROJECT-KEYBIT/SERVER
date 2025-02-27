package com.msa.product.application.port.out;

import com.msa.product.domain.product.event.TagChanged;

public interface TagChangeEventPublisher {
    void occurTagChangedEvent(TagChanged tagUpdated);
}
