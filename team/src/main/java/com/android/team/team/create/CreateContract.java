package com.android.team.team.create;

import android.content.Context;

import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.base.mvp.BaseMvpView;

/**
 * Created by kiddo on 17-9-17.
 */

public interface CreateContract {
    interface View extends BaseMvpView<Presenter>{
        String getTeamName();
        String getDescription();
    }

    abstract class Presenter extends BaseMvpPresenter<View>{

        public Presenter(Context context, View view) {
            super(context, view);
        }

        abstract void create();

        abstract void uploadTeamInfo(String teamGroup, String imageId);
    }
}
