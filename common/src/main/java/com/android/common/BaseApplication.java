package com.android.common;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.common.network.OkHttpUtils;
import com.android.common.network.log.LoggerInterceptor;

import okhttp3.OkHttpClient;


/**
 * Created by kiddo on 17-8-2.
 */

public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication ;

    public static BaseApplication getContext(){
        return mBaseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        initOkHttp();
        initARouter();
    }

    private void initARouter() {
        if (com.alibaba.android.arouter.BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    private void initOkHttp(){
//        List<Interceptor> applicationInterceptors = new ArrayList<>();
//        applicationInterceptors.add(new LoggerInterceptor("", true));
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new LoggerInterceptor("", true));
        OkHttpClient okHttpClient = clientBuilder.build();
        OkHttpUtils.initClient(okHttpClient);
    }

}
