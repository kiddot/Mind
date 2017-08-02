package com.android.common.network.callback;

/**
 * Created by kiddo on 17-8-2.
 */

public interface IGenericsSerializator {
    <T> T transform(String response, Class<T> classOfT);
}
