package com.android.main.login;

import android.os.Bundle;
import android.view.View;

import com.android.common.base.componet.BaseMvpActivity;
import com.android.main.R;
import com.android.main.R2;
import com.dd.processbutton.FlatButton;

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


    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(LoginPresenter presenter, Bundle savedInstanceState) {
        initView();

    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this, this);
    }

    private void initView() {
        EventBus.getDefault().register(this);
    }

    public void login(View view) {
        getPresenter().login();
    }

    public void signUp(View view) {
//        RegisterActivity.startActivity(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveLoginEvent(LoginEvent event) {
        boolean isSuccess = event.isSuccess();
//        Log.d(TAG, "onReceiveLoginEvent: " + isSuccess);
//        if (isSuccess){
//            mLoginBinding.loginBtnLogin.setProgress(100);
//            MainActivity.startActivity(this);
//            finish();
//        } else {
//            showToast("登录失败，请检查用户名和密码是否有误");
//            mLoginBinding.loginBtnLogin.setProgress(-1);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

}
