package com.android.team.team.create;

import android.content.Context;

import com.alexvasilkov.android.commons.ui.Views;
import com.android.common.bean.Team;
import com.android.common.dao.AppDaoManager;
import com.android.common.manager.UserManager;
import com.android.team.R;
import com.android.team.team.CreateTeamEvent;
import com.android.team.team.common.CommonAPI;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by kiddo on 17-9-17.
 */

public class CreatePresenter extends CreateContract.Presenter {
    private Context context;
    public CreatePresenter(Context context, CreateContract.View view) {
        super(context, view);
    }

    @Override
    void create() {
        int imageId = R.drawable.starry_night;
        Team team = new Team(imageId, getView().getTeamName(), getView().getDescription(), null);
        EventBus.getDefault().post(new CreateTeamEvent(team));
        AppDaoManager.get(UserManager.getInstance(context).getUserName()).insert(team);
    }

    @Override
    void uploadTeamInfo(String teamGroup, String imageId) {
        CommonAPI.uploadTeamInfo(getView().getTeamName(),getView().getDescription(),teamGroup,imageId);
    }
}
