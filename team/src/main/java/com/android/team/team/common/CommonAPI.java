package com.android.team.team.common;

import android.util.Log;

import com.android.common.AppConstant;
import com.android.common.base.util.GsonUtil;
import com.android.common.bean.GsonUser;
import com.android.common.bean.Team;
import com.android.common.network.builder.GetBuilder;
import com.android.common.network.request.RequestCall;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by kiddo on 17-9-17.
 */

public class CommonAPI {
    private static final String TAG = "CommonAPI";

    private static final String REGISTER_TEAM_URL = AppConstant.Url + "/team/registerTeam";

    public static void  uploadTeamInfo(String teamName, String description, String teamGroup, String imageId){
        GetBuilder builder = new GetBuilder()
                .url(REGISTER_TEAM_URL)
                .addParams("teamName", teamName)
                .addParams("description", description)
                .addParams("teamGroup", teamGroup)
                .addParams("imageId", imageId);
        RequestCall call = builder.build();
        try {
            Response response = call.execute();
            String result = response.body().string();
            Log.d(TAG, "uploadTeamInfo: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
