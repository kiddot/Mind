package com.android.teamtask.task;

import android.content.Context;

import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.base.mvp.BaseMvpView;

/**
 * Created by kiddo on 17-8-8.
 */

public interface TaskContract {
    interface View extends BaseMvpView<Presenter>{

    }

    abstract class Presenter extends BaseMvpPresenter<View>{

        public Presenter(Context context, View view) {
            super(context, view);
        }
    }
}
