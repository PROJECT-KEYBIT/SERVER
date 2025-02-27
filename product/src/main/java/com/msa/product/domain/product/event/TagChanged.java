package com.msa.product.domain.product.event;

import java.util.Set;

public record TagChanged(Set<String> tagNames) { }

