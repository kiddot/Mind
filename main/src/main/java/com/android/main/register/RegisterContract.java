package com.android.main.register;

import android.content.Context;

import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.base.mvp.BaseMvpView;

/**
 * Created by kiddo on 17-8-4.
 */

public interface RegisterContract {
    interface View extends BaseMvpView<Presenter>{
        String getUserName();
        String getPassword();
        String getEmail();
        String getSex();
        String getPhone();
    }

    abstract class Presenter extends BaseMvpPresenter<View>{

        public Presenter(Context context, View view) {
            super(context, view);
        }

        abstract void register();
    }
}
