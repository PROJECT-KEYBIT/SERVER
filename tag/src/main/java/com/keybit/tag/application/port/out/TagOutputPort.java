package com.keybit.tag.application.port.out;

import com.keybit.tag.domain.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagOutputPort {

    Tag loadTagByName(String name);
    List<Tag> loadTagsByNameIn(Set<String> names);
    List<Tag> saveAll(Set<Tag> tags);
}
