package com.android.main.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.common.base.componet.BaseMvpActivity;
import com.android.main.R;
import com.android.main.R2;
import com.dd.processbutton.FlatButton;
import com.dd.processbutton.iml.ActionProcessButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.james.biuedittext.BiuEditText;

/**
 * Created by kiddo on 17-8-3.
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {
    public static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R2.id.login_et_username)
    BiuEditText mUserName;
    @BindView(R2.id.login_et_password)
    BiuEditText mPassword;
    @BindView(R2.id.login_btn_register)
    FlatButton mRegister;
    @BindView(R2.id.login_btn_login)
    ActionProcessButton mLoginBtnLogin;

    @Override
    protected void init(LoginPresenter presenter, Bundle savedInstanceState) {

    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this, this);
    }

    public void login(View view) {
        getPresenter().login();
    }

    public void signUp(View view) {
        ARouter.getInstance().build("/main/registerActivity").navigation();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveLoginEvent(LoginEvent event) {
        boolean isSuccess = event.isSuccess();
        Log.d(TAG, "onReceiveLoginEvent: " + isSuccess);
        if (isSuccess) {
            mLoginBtnLogin.setProgress(100);
            //MainActivity.startActivity(this);
            //finish();
        } else {
            showToast("登录失败，请检查用户名和密码是否有误");
            mLoginBtnLogin.setProgress(-1);
        }
    }

    @Override
    public String getUserName() {
        return mUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

}
