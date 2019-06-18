package com.simplicity.poc.lambda.util;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil {
    private StringUtil() {
    }

    public static String reverseString(String input) {
        if (StringUtils.isEmpty(input)) {
            return input;
        }
        return new StringBuilder(input).reverse().toString();
    }
}
