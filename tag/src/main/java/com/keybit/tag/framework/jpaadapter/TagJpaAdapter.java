package com.keybit.tag.framework.jpaadapter;

import com.keybit.tag.application.port.out.TagOutputPort;
import com.keybit.tag.domain.tag.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class TagJpaAdapter implements TagOutputPort {

    private final TagRepository tagRepository;

    @Override
    public Tag loadTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> loadTagsByNameIn(Set<String> names) {
        return tagRepository.findByNameIn(names);
    }

    @Override
    public void updateAllTags(Set<Tag> tags) {
        tagRepository.saveAll(tags);
    }

    @Override
    public Set<Tag> loadTagsForTagsChangedInProduct(Set<String> names, String productId) {
        return tagRepository.findByNameInAndProductIdsNotContaining(names, productId);
    }

    @Override
    public void saveAll(Set<Tag> tags) {
        tagRepository.insert(tags);
    }
}
