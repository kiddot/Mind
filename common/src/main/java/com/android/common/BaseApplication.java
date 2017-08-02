package com.android.common;

import android.app.Application;

/**
 * Created by kiddo on 17-8-2.
 */

public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication ;

    public static BaseApplication getInstance(){
        return mBaseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
    }
}
