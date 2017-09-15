package com.android.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.common.manager.UserManager;
import com.android.common.network.OkHttpUtils;
import com.android.common.network.log.LoggerInterceptor;
import com.android.common.push.InitPush;
import com.android.common.push.Notifications;
import com.android.common.push.Push;
import com.android.tph.client.ClientConfig;

import okhttp3.OkHttpClient;


/**
 * Created by kiddo on 17-8-2.
 */

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    private static final String IP = "http://192.168.199.133:30000";
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
        initPush();
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

    private void initPush(){
        Log.d(TAG, "initPush: ");
        Notifications.I.init(this.getApplicationContext());
        Notifications.I.setSmallIcon(R.drawable.fab_add);
        Notifications.I.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fab_add));
        String userName = UserManager.getInstance(this).getUserName();
        if (userName == null){
            userName = "kiddo";
        }
        InitPush.getInstance().initPush(this, IP, userName);
        InitPush.getInstance().startPush(this);
        InitPush.getInstance().bindUser(userName);
    }

}
