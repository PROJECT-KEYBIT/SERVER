package com.keybit.tag.framework.jpaadapter;

import com.keybit.tag.application.port.out.TagOutputPort;
import com.keybit.tag.domain.entity.Tag;
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
    public List<Tag> saveAll(List<Tag> tags) {
        return List.of();
    }
}
