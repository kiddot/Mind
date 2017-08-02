package com.android.common.network.utils;

import android.util.Log;

/**
 * Created by kiddo on 17-8-2.
 */

public class Logger {
    private static boolean debug = false;

    public static void e(String msg)
    {
        if (debug)
        {
            Log.e("OkHttp", msg);
        }
    }
}
