package com.msa.product.domain.product.pattern;

import java.util.regex.Pattern;

public abstract class TagPattern {
    public static final Pattern PRODUCT_TAG_PATTERN = Pattern.compile("#[\\w가-힣]+");
}
