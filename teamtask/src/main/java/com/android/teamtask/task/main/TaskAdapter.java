package com.android.teamtask.task.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.common.base.componet.ISlidingUpPanel;
import com.android.common.base.componet.SlidingUpPanelLayout;
import com.android.common.base.util.Dp2Px;
import com.android.common.bean.Task;
import com.android.teamtask.task.view.BaseTaskPanel;
import com.android.teamtask.task.view.TaskPanelView;

import java.util.List;

import static com.android.common.base.componet.SlidingUpPanelLayout.EXPANDED;
import static com.android.common.base.componet.SlidingUpPanelLayout.COLLAPSED;

/**
 * Created by kiddo on 17-8-10.
 */

public class TaskAdapter extends SlidingUpPanelLayout.Adapter {
    private static final String TAG = "TaskAdapter";
    private BasePanelListener mBasePanelListener;
    private List<Task> mTasks;
    private Context mContext;

    public TaskAdapter(Context context, List<Task> list) {
        mTasks = list;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    @NonNull
    @Override
    public ISlidingUpPanel onCreateSlidingPanel(int position) {
        TaskPanelView panel = new TaskPanelView(mContext);
        panel.setFloor(mTasks.size() - position);
        panel.setPanelHeight(mTasks.size() == 1 ? Dp2Px.dp2px(120) : Dp2Px.dp2px(80));
//        if (position == 0) {
//            panel.setSlideState(EXPANDED);
//            panel.setEnabled(false);
//        } else {
//            panel.setSlideState(HIDDEN);
//            panel.setEnabled(true);
//        }
        panel.setSlideState(COLLAPSED);
        panel.setEnabled(true);

        return panel;
    }

    @Override
    public void onBindView(final ISlidingUpPanel panel, int position) {
        if (mTasks.size() == 0)
            return;

        BaseTaskPanel BasePanel = (BaseTaskPanel) panel;
        BasePanel.setTaskModel(mTasks.get(position));
        BasePanel.setClickable(true);
        BasePanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBasePanelListener.clickBasePanel(panel.getSlideState());
            }
        });
    }

    public void setBasePanelListener(BasePanelListener basePanelListener){
        mBasePanelListener = basePanelListener;
    }

    public interface BasePanelListener{
        void clickBasePanel(int state);
    }
}
