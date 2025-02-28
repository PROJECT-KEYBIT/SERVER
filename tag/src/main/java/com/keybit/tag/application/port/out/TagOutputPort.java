package com.keybit.tag.application.port.out;

import com.keybit.tag.domain.entity.Tag;

public interface TagOutputPort {

    Tag loadTagByName(String name);
}
