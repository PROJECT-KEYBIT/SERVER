package com.keybit.tag.application.port.in;

import com.keybit.tag.domain.event.TagChanged;

public interface ProductTagChangeUsecase {
    void productTagChanged(TagChanged tagChanged);
}
