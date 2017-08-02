package com.android.common.network.builder;

import com.android.common.network.OkHttpUtils;
import com.android.common.network.request.OtherRequest;
import com.android.common.network.request.RequestCall;

/**
 * Created by kiddo on 17-8-2.
 */

public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers, id).build();
    }
}
