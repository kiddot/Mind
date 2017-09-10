package com.android.teamtask.task.publish;

import android.content.Context;

import com.android.common.bean.User;
import com.android.teamtask.task.common.TaskApi;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by kiddo on 17-8-17.
 */

public class PublishPresenter extends PublishContract.Presenter {
    public PublishPresenter(Context context, PublishContract.View view) {
        super(context, view);
    }

    @Override
    void publishTask() {
        Task.call(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return TaskApi.publishTask(getView().getUserName(), getView().getTitleLine(),
                        getView().getStartDate(), getView().getEndDate(), getView().getContent(),
                        getView().getTeamName(), getView().getSender(), getView().getReceiver(), 0);
            }
        }, sHTTPExecutor).continueWith(new Continuation<Boolean, Boolean>() {
            @Override
            public Boolean then(Task<Boolean> task) throws Exception {
                if (task.getResult()){
                    //succcess
                } else {
                    //fail
                }
                return true;
            }
        }, sWORKExecutor);
    }
}
