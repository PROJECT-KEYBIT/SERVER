package com.keybit.tag.application.port.out;

import com.keybit.tag.domain.tag.model.Tag;

import java.util.List;
import java.util.Set;

public interface TagOutputPort {

    Tag loadTagByName(String name);
    List<Tag> loadTagsByNameIn(Set<String> names);
    void updateAllTags(Set<Tag> tags);
    Set<Tag> loadTagsForTagsChangedInProduct (Set<String> names, String productId);
    void saveAll(Set<Tag> tags);
}
