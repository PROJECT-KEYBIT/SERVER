package com.keybit.tag.framework.jpaadaptor;

import com.keybit.tag.application.port.out.TagOutputPort;
import com.keybit.tag.domain.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TagJpaAdapter implements TagOutputPort {

    private final TagRepository tagRepository;

    @Override
    public Tag loadTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
