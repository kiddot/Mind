package com.android.team.team.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alexvasilkov.android.commons.ui.Views;
import com.android.common.base.componet.BaseActivity;
import com.android.common.base.componet.BaseMvpActivity;
import com.android.common.base.mvp.BaseMvpPresenter;
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

public class CreateTeamActivity extends BaseMvpActivity<CreatePresenter> implements CreateContract.View {
    BiuEditText createEtName;
    BiuEditText createEtDescription;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, CreateTeamActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_create;
    }

    public void create(View view) {
        getPresenter().create();
        getPresenter().uploadTeamInfo(null, String.valueOf(R.drawable.starry_night));
        finish();
    }

    @Override
    public String getTeamName() {
        return createEtName.getText().toString().trim();
    }

    @Override
    public String getDescription() {
        return createEtDescription.getText().toString().trim();
    }

    @Override
    protected void init(CreatePresenter presenter, Bundle savedInstanceState) {
        createEtName = Views.find(this, R.id.create_et_name);
        createEtDescription = Views.find(this, R.id.create_et_description);
    }

    @Override
    protected CreatePresenter onCreatePresenter() {
        return new CreatePresenter(this, this);
    }
}
