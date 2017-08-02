package com.android.common.network.builder;

import com.android.common.network.request.PostStringRequest;
import com.android.common.network.request.RequestCall;

import okhttp3.MediaType;

/**
 * Created by kiddo on 17-8-2.
 */

public class PostStringBuilder extends OkHttpRequestBuilder<PostStringBuilder>
{
    private String content;
    private MediaType mediaType;


    public PostStringBuilder content(String content)
    {
        this.content = content;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostStringRequest(url, tag, params, headers, content, mediaType,id).build();
    }


}
