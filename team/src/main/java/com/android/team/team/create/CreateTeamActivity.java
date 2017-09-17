package com.android.team.team.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alexvasilkov.android.commons.ui.Views;
import com.android.common.base.componet.BaseActivity;
import com.android.common.bean.Team;
import com.android.common.dao.AppDaoManager;
import com.android.common.manager.UserManager;
import com.android.team.R;
import com.android.team.R2;
import com.android.team.team.CreateTeamEvent;
import com.dd.processbutton.iml.ActionProcessButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.james.biuedittext.BiuEditText;

/**
 * Created by kiddo on 17-9-10.
 */

public class CreateTeamActivity extends BaseActivity {
    BiuEditText createEtName;
    BiuEditText createEtDescription;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, CreateTeamActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_create;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void create(View view) {
        createEtName = Views.find(this, R.id.create_et_name);
        createEtDescription = Views.find(this, R.id.create_et_description);
        String teamName = createEtName.getText().toString().trim();
        String description = createEtDescription.getText().toString().trim();
        int imageId = R.drawable.starry_night;
        Team team = new Team(imageId, teamName, description, null);
        EventBus.getDefault().post(new CreateTeamEvent(team));
        AppDaoManager.get(UserManager.getInstance(this).getUserName()).insert(team);
        finish();
    }
}
