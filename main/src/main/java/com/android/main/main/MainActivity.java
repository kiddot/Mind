package com.android.main.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.common.base.componet.BaseActivity;
import com.android.common.base.componet.PermissionListener;
import com.android.common.manager.UserManager;
import com.android.common.push.InitPush;
import com.android.common.push.Notifications;
import com.android.main.R;
import com.android.main.R2;
import com.android.main.login.LoginEvent;
import com.github.clans.fab.FloatingActionButton;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kiddo on 17-8-4.
 */

@Route(path = "/main/mainActivity")
public class MainActivity extends BaseActivity implements PermissionListener{
    private static final String TAG = "MainActivity";
    @BindView(R.id.menu_item_one)
    FloatingActionButton mMenuItemOne;
    @BindView(R.id.menu_item_second)
    FloatingActionButton mMenuItemSecond;
    @BindView(R.id.menu_item_third)
    FloatingActionButton mMenuItemThird;
    @BindView(R.id.menu_item_fourth)
    FloatingActionButton mMenuItemFourth;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        requestRuntimePermission(new String[]{Manifest.permission.READ_PHONE_STATE}, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveLoginEvent(LoginEvent event) {
    }

    @OnClick({R2.id.menu_item_one, R2.id.menu_item_second, R2.id.menu_item_third, R2.id.menu_item_fourth})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.menu_item_one){

        } else if (id == R.id.menu_item_second){

        } else if (id == R.id.menu_item_third){

        } else if (id == R.id.menu_item_fourth){

        }
    }


    private static final String IP = "http://192.168.199.133:30000";

    private void initPush(){
        Log.d(TAG, "initPush: ");
        Notifications.I.init(this.getApplicationContext());
        Notifications.I.setSmallIcon(com.android.common.R.drawable.fab_add);
        Notifications.I.setLargeIcon(BitmapFactory.decodeResource(getResources(), com.android.common.R.drawable.fab_add));
        String userName = UserManager.getInstance(this).getUserName();
        if (userName == null){
            userName = "kiddo";
        }
        InitPush.getInstance().initPush(this, IP, userName, getDeviceId());
        InitPush.getInstance().startPush(this);
        InitPush.getInstance().bindUser(userName);
    }

    private String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Activity.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            String time = Long.toString((System.currentTimeMillis() / (1000 * 60 * 60)));
            deviceId = time + time;
        }
        return deviceId;
    }

    @Override
    public void onGranted() {
        initPush();
    }

    @Override
    public void onDenied(ArrayList<String> list) {
        Toast.makeText(this, list.get(0) + "权限被拒绝,请到手机设置权限!", Toast.LENGTH_SHORT).show();
    }
}
