package com.android.mind;


import com.alibaba.android.arouter.*;
import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.common.BaseApplication;

/**
 * <p>这里是整个组件化项目管理各个组件的地方，所有需要使用的组件必须在此声明</p>
 *
 * Created by kiddo on 17-8-2.
 */
public class MindApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }
}
