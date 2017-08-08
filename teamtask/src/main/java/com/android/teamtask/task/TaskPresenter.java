package com.android.teamtask.task;

import android.content.Context;

/**
 * Created by kiddo on 17-8-8.
 */

public class TaskPresenter extends TaskContract.Presenter {
    public TaskPresenter(Context context, TaskContract.View view) {
        super(context, view);
    }
}
