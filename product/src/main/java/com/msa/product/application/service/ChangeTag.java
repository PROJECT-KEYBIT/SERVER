package com.msa.product.application.service;

import com.msa.product.application.port.in.ChangeTagUsecase;
import com.msa.product.application.port.out.TagChangeEventPublisher;
import com.msa.product.application.port.out.ProductOutputPort;
import com.msa.product.domain.product.event.TagChanged;
import com.msa.product.domain.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Matcher;

import static com.msa.product.domain.product.pattern.TagPattern.PRODUCT_TAG_PATTERN;

@Service
@RequiredArgsConstructor
public class ChangeTag implements ChangeTagUsecase {

    private final ProductOutputPort productOutputPort;
    private final TagChangeEventPublisher tagChangeEventPublisher;

    @Override
    public void changeTags(String productNo, String tags) {
        Product product = productOutputPort.loadProduct(productNo)
                .orElseThrow(() -> new NoSuchElementException(productNo + ": 없는 상품 번호 입니다."));

        Set<String> tagNames = parseTagNames(tags);
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
