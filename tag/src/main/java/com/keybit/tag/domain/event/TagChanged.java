package com.keybit.tag.domain.event;

import java.util.Set;

public record TagChanged(String ProductNo, Set<String> tagNames) { }

