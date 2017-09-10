package com.android.teamtask.task.publish;

import android.content.Context;

import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.base.mvp.BaseMvpView;

/**
 * Created by kiddo on 17-8-17.
 */

public interface PublishContract {
    interface View extends BaseMvpView<Presenter>{
        String getUserName();
        String getTitleLine();
        String getStartDate();
        String getEndDate();
        String getContent();
        String getSender();
        String getReceiver();
        String getTeamName();
    }

    abstract class Presenter extends BaseMvpPresenter<View>{

        public Presenter(Context context, View view) {
            super(context, view);
        }

        abstract void publishTask();
    }
}
