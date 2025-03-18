package com.keybit.tag.application.service;

import com.keybit.tag.application.port.in.ProductTagChangeUsecase;
import com.keybit.tag.application.port.out.TagOutputPort;
import com.keybit.tag.domain.tag.event.TagChanged;
import com.keybit.tag.domain.tag.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Set<Tag> tags = outputPort.loadTagsForTagsChangedInProduct(tagNames, productId);

        // 이미 있는 태그 객체에 상품 코드 추가
        addProductIdToAlreadyExistingTags(productId, tags);

        // 존재하지 않는 태그에 상품 코드 추가 해 저장
        createNewTagsAndAddProductId(productId, tagNames);
    }

    private void addProductIdToAlreadyExistingTags(String productId, Set<Tag> tags) {
        tags.forEach(tag -> tag.addProductId(productId));
        outputPort.updateAllTags(tags);
    }

    private void createNewTagsAndAddProductId(String productId, Set<String> tagNames) {
        Set<Tag> newTags =
                tagNames.stream()
                        .map(tagName -> Tag.register(tagName, productId))
                        .collect(Collectors.toUnmodifiableSet());

        outputPort.saveAll(newTags);
    }
}
