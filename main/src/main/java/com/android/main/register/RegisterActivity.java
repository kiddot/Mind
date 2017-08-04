package com.android.main.register;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.common.base.componet.BaseMvpActivity;
import com.android.main.R;
import com.dd.processbutton.iml.ActionProcessButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.james.biuedittext.BiuEditText;

/**
 * Created by kiddo on 17-8-4.
 */

@Route(path = "/main/registerActivity")
public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements RegisterContract.View {
    private static final String TAG = "RegisterActivity";
    @BindView(R.id.register_et_username)
    BiuEditText mRegisterEtUsername;
    @BindView(R.id.register_et_password)
    BiuEditText mRegisterEtPassword;
    @BindView(R.id.register_et_pwd)
    BiuEditText mRegisterEtPwd;
    @BindView(R.id.register_et_sex)
    BiuEditText mRegisterEtSex;
    @BindView(R.id.register_et_email)
    BiuEditText mRegisterEtEmail;
    @BindView(R.id.register_et_phone)
    BiuEditText mRegisterEtPhone;
    @BindView(R.id.register_btn_register)
    ActionProcessButton mRegisterBtnRegister;

    @Override
    protected void init(RegisterPresenter presenter, Bundle savedInstanceState) {

    }

    @Override
    protected RegisterPresenter onCreatePresenter() {
        return new RegisterPresenter(this, this);
    }

    public void register(View view){
        getPresenter().register();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveLoginEvent(RegisterEvent event){
        int code = event.getCode();
        Log.d(TAG, "onReceiveRegisterEvent: " + code);
        if (code == 200){
            mRegisterBtnRegister.setProgress(100);
            showToast("注册成功");
        } else if (code == 205){
            showToast("注册失败，该用户名已经被注册");
            mRegisterBtnRegister.setProgress(-1);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public String getUserName() {
        return mRegisterEtUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mRegisterEtPassword.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return mRegisterEtEmail.getText().toString().trim();
    }

    @Override
    public String getSex() {
        return mRegisterEtSex.getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return mRegisterEtPhone.getText().toString().trim();
    }
}
