package com.android.common.network.builder;

import com.android.common.network.request.PostFileRequest;
import com.android.common.network.request.RequestCall;

import java.io.File;

import okhttp3.MediaType;

/**
 * Created by kiddo on 17-8-2.
 */

public class PostFileBuilder extends OkHttpRequestBuilder<PostFileBuilder>
{
    private File file;
    private MediaType mediaType;


    public OkHttpRequestBuilder file(File file)
    {
        this.file = file;
        return this;
    }

    public OkHttpRequestBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostFileRequest(url, tag, params, headers, file, mediaType,id).build();
    }


}
