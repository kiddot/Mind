package com.android.main.login;

import android.content.Context;

import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.base.mvp.BaseMvpView;

/**
 * Created by kiddo on 17-8-3.
 */

public interface LoginContract {
    interface View extends BaseMvpView<Presenter> {
        String getUserName();
        String getPassword();
    }

    abstract class Presenter extends BaseMvpPresenter<View>{

        public Presenter(Context context, View view) {
            super(context, view);
        }

        abstract void login();

        abstract void signOn();
    }
}
