package com.msa.product.domain.product.event;

import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
public class TagUpdated {

    private Set<String> tagNames = new LinkedHashSet<>();
}
