package com.simplicity.poc.lambda.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {

    @Test
    public void reverseString_withTestString_returnsReverseString() {
        assertEquals("gnirtS tseT", StringUtil.reverseString("Test String"));
    }

    @Test
    public void reverseString_withEmptyString_returnsEmptyString() {
        assertEquals("", StringUtil.reverseString(""));
    }

    @Test
    public void reverseString_withNullString_returnsNull() {
        assertEquals(null, StringUtil.reverseString(null));
    }
}