package com.android.teamtask.task.common;

import android.util.Log;

import com.android.common.AppConstant;
import com.android.common.base.util.GsonUtil;
import com.android.common.bean.GsonUser;
import com.android.common.bean.Task;
import com.android.common.network.builder.GetBuilder;
import com.android.common.network.request.RequestCall;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by kiddo on 17-8-17.
 */

public class TaskApi {
    private static final String TAG = "TaskApi";
    private static final String TASK_URL = AppConstant.Url + "login.php";

    public static boolean publishTask(String username, String title, String startDate, String endDate,
                                   String content, String teamName, String sender, String receiver,
                                   int priority){
        GetBuilder builder = new GetBuilder()
                .url(TASK_URL)
                .addParams("format", "save")
                .addParams("username", username)
                .addParams("title", title)
                .addParams("startDate", startDate)
                .addParams("endDate", endDate)
                .addParams("content", content)
                .addParams("teamName", teamName)
                .addParams("sender", sender)
                .addParams("receiver", receiver)
                .addParams("priority", String.valueOf(priority));
        RequestCall call = builder.build();
        try {
            Response response = call.execute();
            String result = response.body().string();
            if (result.contains("200")){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void getTask(String username, String teamName, int page, int limit){
        GetBuilder builder = new GetBuilder()
                .url(TASK_URL)
                .addParams("format", "save")
                .addParams("username", username)
                .addParams("page", String.valueOf(page))
                .addParams("limit", String.valueOf(limit))
                .addParams("teamName", teamName);
        RequestCall call = builder.build();
        try {
            Response response = call.execute();
            String result = response.body().string();
            Log.d(TAG, "getTask: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
