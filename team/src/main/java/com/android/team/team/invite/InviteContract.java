package com.android.team.team.invite;

import android.content.Context;

import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.base.mvp.BaseMvpView;

/**
 * Created by kiddo on 17-9-17.
 */

public interface InviteContract  {
    interface View extends BaseMvpView<Presenter>{
        String getInviteName();
    }

    abstract class Presenter extends BaseMvpPresenter<View>{

        public Presenter(Context context, View view) {
            super(context, view);
        }

        abstract void sendInvite();
    }
}
