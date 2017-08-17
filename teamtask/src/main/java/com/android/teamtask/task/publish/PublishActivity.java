package com.android.teamtask.task.publish;

import android.os.Bundle;
import android.view.View;

import com.android.common.base.componet.BaseMvpActivity;
import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.teamtask.R;

/**
 * Created by kiddo on 17-8-17.
 */

public class PublishActivity extends BaseMvpActivity implements PublishContract.View{

    @Override
    protected void init(BaseMvpPresenter presenter, Bundle savedInstanceState) {

    }

    @Override
    protected BaseMvpPresenter onCreatePresenter() {
        return new PublishPresenter(this, this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_publish;
    }

    public void onClickDate(View view){

    }
}
