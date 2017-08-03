package com.android.main.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.common.base.componet.BaseActivity;
import com.android.common.base.util.Check;
import com.android.main.R;
import com.dd.processbutton.iml.ActionProcessButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by kiddo on 17-8-3.
 */

public class LoginActivity extends BaseActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void getLayoutBinding() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
    }

    public void login(View view){
        String username = mLoginBinding.loginEtUsername.getText().toString();
        String password = mLoginBinding.loginEtPassword.getText().toString();
        if (Check.isEmpty(username) || Check.isEmpty(password)){
            showToast("请检查账号或者密码是否为空");
        }else {
            mLoginAndRegister.login(this, "login", username, password);
            mLoginBinding.loginBtnLogin.setProgress(50);
            Log.d(TAG, "用户正在尝试登录  ----ing ");
        }
    }

    public void signUp(View view){
        RegisterActivity.startActivity(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveLoginEvent(LoginEvent event){
        boolean isSuccess = event.isSuccess();
        Log.d(TAG, "onReceiveLoginEvent: " + isSuccess);
        if (isSuccess){
            mLoginBinding.loginBtnLogin.setProgress(100);
            MainActivity.startActivity(this);
            finish();
        } else {
            showToast("登录失败，请检查用户名和密码是否有误");
            mLoginBinding.loginBtnLogin.setProgress(-1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
