package com.android.team.debug;

import com.android.common.BaseApplication;
import com.android.common.manager.UserManager;

/**
 * Created by kiddo on 17-9-10.
 */

public class TeamApplication extends BaseApplication {

    @Override
    public void onCreate() {
        //test
        UserManager.getInstance(this).setUserName("kiddo");

        super.onCreate();


    }
}
