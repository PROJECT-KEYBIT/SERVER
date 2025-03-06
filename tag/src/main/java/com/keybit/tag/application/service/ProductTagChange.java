package com.keybit.tag.application.service;

import com.keybit.tag.application.port.in.ProductTagChangeUsecase;
import com.keybit.tag.application.port.out.TagOutputPort;
import com.keybit.tag.domain.entity.Tag;
import com.keybit.tag.domain.event.TagChanged;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductTagChange implements ProductTagChangeUsecase {

    private final TagOutputPort outputPort;

    @Override
    public void productTagChanged(TagChanged tagChanged) {
        Set<String> tagNames = tagChanged.tagNames();
        Set<String> existingTags = outputPort.loadTagsByNameIn(tagNames).stream()
                .map(Tag::getName)
                .collect(Collectors.toUnmodifiableSet());

        Set<Tag> newlyRegisteredTags = tagNames.stream()
                .filter(name -> !existingTags.contains(name))
                .map(Tag::register)
                .collect(Collectors.toUnmodifiableSet());

        if (!newlyRegisteredTags.isEmpty())
            outputPort.saveAll(newlyRegisteredTags);

        //TODO: 새로 생긴 태그 추가 이벤트 생성
    }
}
