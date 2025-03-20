package com.keybit.tag.application.service;

import com.keybit.tag.application.port.in.ProductTagChangeUsecase;
import com.keybit.tag.application.port.out.TagOutputPort;
import com.keybit.tag.domain.tag.event.TagChanged;
import com.keybit.tag.domain.tag.model.Tag;
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
        // 1. Defensive check for null input
        if (tagChanged == null ||
            tagChanged.tagNames() == null ||
            tagChanged.tagNames().isEmpty())
        { return; }

        String productId = tagChanged.productNo();
        Set<String> tagNames = tagChanged.tagNames();
        List<Tag> tags = outputPort.loadTagsByNameIn(tagNames);

        // 이미 있는 태그 객체에 상품 코드 추가
        addProductIdToAlreadyExistingTags(productId, tags);

        //새로운 태그 추출하기
        Set<Tag> newTags = extractNotExistingTags(tags, tagNames, productId);

        // 존재하지 않는 태그에 상품 코드 추가 해 저장
        outputPort.saveAll(newTags);
    }

    private void addProductIdToAlreadyExistingTags(String productId, List<Tag> tags) {
        tags.stream()
            .filter(tag -> !tag.getProductIds().contains(productId))
            .forEach(tag -> tag.addProductId(productId));
    }

    private Set<Tag> extractNotExistingTags(List<Tag> tags, Set<String> tagNames, String productId) {
        Set<String> existingTagNames = tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toUnmodifiableSet());

        return tagNames.stream()
                       .filter(tagName -> !existingTagNames.contains(tagName))
                       .map(tagName -> Tag.register(tagName, productId))
                       .collect(Collectors.toUnmodifiableSet());
    }
}
