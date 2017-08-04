package com.android.main.login;

import android.util.Log;

import com.android.common.AppConstant;
import com.android.common.base.util.GsonUtil;
import com.android.common.bean.GsonUser;
import com.android.common.bean.User;
import com.android.common.network.builder.GetBuilder;
import com.android.common.network.request.RequestCall;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by kiddo on 17-8-3.
 */

public class LoginApi {
    private static final String TAG = "LoginApi";

    private static final String LOGIN_URL = AppConstant.Url + "login.php";

    public static User login(String userName, String passWord){
        GetBuilder builder = new GetBuilder()
                .url(LOGIN_URL)
                .addParams("format", "login")
                .addParams("username", userName)
                .addParams("password", passWord);
        RequestCall call = builder.build();
        try {
            Response response = call.execute();
            String result = response.body().string();
            GsonUser gsonUser = GsonUtil.GsonToBean(result, GsonUser.class);
            Log.d(TAG, "login: " + result + "code:" + gsonUser.getCode());
            return gsonUser.getUser();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
