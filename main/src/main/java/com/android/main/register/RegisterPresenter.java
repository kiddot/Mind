package com.android.main.register;

import android.content.Context;

import com.android.common.bean.User;
import com.android.main.common.MainApi;
import com.android.main.login.LoginEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by kiddo on 17-8-4.
 */

public class RegisterPresenter extends RegisterContract.Presenter {
    public RegisterPresenter(Context context, RegisterContract.View view) {
        super(context, view);
    }

    @Override
    void register() {
        Task.call(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return MainApi.register(getView().getUserName(), getView().getPassword(), getView().getSex(), getView().getEmail(), getView().getPhone());
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
}
