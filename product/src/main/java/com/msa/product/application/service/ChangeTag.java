package com.msa.product.application.service;

import com.msa.product.application.port.in.ChangeTagUsecase;
import com.msa.product.application.port.out.ProductOutputPort;
import com.msa.product.application.port.out.TagChangeEventPublisher;
import com.msa.product.domain.product.event.TagChanged;
import com.msa.product.domain.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Matcher;

import static com.msa.product.domain.product.pattern.TagPattern.PRODUCT_TAG_PATTERN;

@Service
@Transactional
@RequiredArgsConstructor
public class ChangeTag implements ChangeTagUsecase {

    private final TagChangeEventPublisher tagChangeEventPublisher;
    private final ProductOutputPort outputPort;

    @Override
    public void changeTags(String productNo, String tags) {
        Set<String> tagNames = parseTagNames(tags);

        Product product = outputPort.loadProduct(productNo)
                .orElseThrow(() -> new NoSuchElementException(productNo + ": 없는 상품 번호입니다."));

        product.changeTags(tags);

        tagChangeEventPublisher.occurTagChangedEvent(new TagChanged(productNo, tagNames));
    }

    private Set<String> parseTagNames(String tags) {
        if (tags == null) return Set.of();

        Matcher matcher = PRODUCT_TAG_PATTERN.matcher(tags.strip());

        Set<String> tagNames = new LinkedHashSet<>();

        while (matcher.find())
            tagNames.add(matcher.group().replace("#", ""));

        return Set.copyOf(tagNames);
    }
}
