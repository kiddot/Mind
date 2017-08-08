package com.android.teamtask.task;

import android.os.Bundle;

import com.android.common.base.componet.BaseMvpActivity;
import com.android.teamtask.R;

/**
 * Created by kiddo on 17-8-8.
 */

public class TaskActivity extends BaseMvpActivity<TaskPresenter> implements TaskContract.View {
    private static final String TAG = "TaskActivity";

    @Override
    protected void init(TaskPresenter presenter, Bundle savedInstanceState) {
        initData();
    }

    private void initData() {

    }

    @Override
    protected TaskPresenter onCreatePresenter() {
        return new TaskPresenter(this, this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_task;
    }
}
