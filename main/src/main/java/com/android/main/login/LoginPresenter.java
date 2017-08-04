package com.android.main.login;

import android.content.Context;

import com.android.common.bean.User;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by kiddo on 17-8-3.
 */

public class LoginPresenter extends LoginContract.Presenter {
    public LoginPresenter(Context context, LoginContract.View view) {
        super(context, view);
    }

    @Override
    void login() {
        Task.call(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return LoginApi.login(getView().getUserName(), getView().getPassword());
            }
        }, sHTTPExecutor).continueWith(new Continuation<User, Boolean>() {
            @Override
            public Boolean then(Task<User> task) throws Exception {
                if (task.getResult() != null){
                    EventBus.getDefault().post(new LoginEvent(true));
                } else {
                    EventBus.getDefault().post(new LoginEvent(false));
                }
                return true;
            }
        }, sWORKExecutor);
    }

    @Override
    void signOn() {

    }
}
