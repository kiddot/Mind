package com.android.common.network.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by kiddo on 17-8-2.
 */

public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException
    {
        return response.body().string();
    }
}
