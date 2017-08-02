package com.android.common.network.builder;

import java.util.Map;

/**
 * Created by kiddo on 17-8-2.
 */

public interface HasParamsable {
    OkHttpRequestBuilder params(Map<String, String> params);
    OkHttpRequestBuilder addParams(String key, String val);
}
