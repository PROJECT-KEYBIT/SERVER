package com.msa.product.domain.product.event;

import java.util.Set;

public record TagUpdated(Set<String> tagNames) { }

