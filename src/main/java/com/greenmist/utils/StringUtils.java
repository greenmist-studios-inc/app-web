package com.greenmist.utils;

/**
 * Created by eckob on 10/5/2016.
 */
public class StringUtils {

    public static boolean isBlank(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNotBlank(String string) {
        return string != null && !string.isEmpty();
    }
}
