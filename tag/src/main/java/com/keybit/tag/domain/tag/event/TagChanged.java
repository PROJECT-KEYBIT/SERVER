package com.keybit.tag.domain.tag.event;

import java.util.Set;

public record TagChanged(String productNo, Set<String> tagNames) { }

