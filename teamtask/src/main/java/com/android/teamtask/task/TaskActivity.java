package com.android.teamtask.task;

import android.os.Bundle;

import com.android.common.base.componet.BaseMvpActivity;

/**
 * Created by kiddo on 17-8-8.
 */

public class TaskActivity extends BaseMvpActivity<TaskPresenter> implements TaskContract.View {
    private static final String TAG = "TaskActivity";

    @Override
    protected void init(TaskPresenter presenter, Bundle savedInstanceState) {

    }

    @Override
    protected TaskPresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }
}
