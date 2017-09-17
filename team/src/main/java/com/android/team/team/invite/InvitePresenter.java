package com.android.team.team.invite;

import android.content.Context;

import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.base.mvp.BaseMvpView;
import com.android.common.push.Push;
import com.android.tph.api.Constants;

/**
 * Created by kiddo on 17-9-17.
 */

public class InvitePresenter extends InviteContract.Presenter {
    private final String INVITE_TYPE = "invite";

    public InvitePresenter(Context context, InviteContract.View view) {
        super(context, view);
    }

    @Override
    void sendInvite() {
        Push.I.sendPush(INVITE_TYPE.getBytes(Constants.UTF_8), getView().getInviteName());
    }
}
