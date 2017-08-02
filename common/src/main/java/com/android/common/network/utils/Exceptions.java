package com.android.common.network.utils;

/**
 * Created by kiddo on 17-8-2.
 */

public class Exceptions {
    public static void illegalArgument(String msg, Object... params) {
        throw new IllegalArgumentException(String.format(msg, params));
    }
}
