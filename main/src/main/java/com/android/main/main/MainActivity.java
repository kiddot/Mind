package com.android.main.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.common.base.componet.BaseActivity;
import com.android.main.R;
import com.android.main.R2;
import com.android.main.login.LoginEvent;
import com.github.clans.fab.FloatingActionButton;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kiddo on 17-8-4.
 */

@Route(path = "/main/mainActivity")
public class MainActivity extends BaseActivity {
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
}
