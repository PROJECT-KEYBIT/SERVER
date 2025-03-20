package com.keybit.tag.application.port.out;

import com.keybit.tag.domain.tag.model.Tag;

import java.util.List;
import java.util.Set;

public interface TagOutputPort {

    List<Tag> loadTagsByNameIn(Set<String> names);
    void saveAll(Set<Tag> tags);
}
