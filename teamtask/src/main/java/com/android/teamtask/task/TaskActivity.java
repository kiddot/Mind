package com.android.teamtask.task;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.android.common.base.componet.BaseMvpActivity;
import com.android.common.base.componet.ISlidingUpPanel;
import com.android.common.base.componet.SlidingUpPanelLayout;
import com.android.common.base.util.Dp2Px;
import com.android.common.bean.Task;
import com.android.teamtask.R;
import com.android.teamtask.R2;
import com.android.teamtask.task.view.BaseTaskPanel;
import com.android.teamtask.task.view.TaskPanelView;
import com.xw.repo.VectorCompatTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.common.base.componet.SlidingUpPanelLayout.COLLAPSED;
import static com.android.common.base.componet.SlidingUpPanelLayout.EXPANDED;
import static com.android.common.base.componet.SlidingUpPanelLayout.HIDDEN;


/**
 * Created by kiddo on 17-8-8.
 */

public class TaskActivity extends BaseMvpActivity<TaskPresenter> implements TaskContract.View,
            TaskAdapter.BasePanelListener{
    private static final String TAG = "TaskActivity";
    @BindView(R2.id.task_tv_add)
    VectorCompatTextView mAddCityText;
    @BindView(R2.id.sliding_up_panel_layout)
    SlidingUpPanelLayout mSlidingUpPanelLayout;
    private List<Task> mTasks;
    private TaskAdapter mAdapter;

    @Override
    protected void init(TaskPresenter presenter, Bundle savedInstanceState) {
        initData();
        initView();
    }

    private void initView() {
        if (mSlidingUpPanelLayout == null){
            mSlidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_up_panel_layout);
        }

        mSlidingUpPanelLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListenerAdapter(){
            @Override
            public void onPanelExpanded(ISlidingUpPanel panel) {
                if (panel instanceof BaseTaskPanel) {
                    int count = mSlidingUpPanelLayout.getChildCount();
                    // 如果被展开的Panel不是距离屏幕顶部最近（floor值最大）那个，做如下处理，再被收起时已是距屏幕顶部最近
                    if (((BaseTaskPanel) panel).getFloor() != count - 1) {

                        mSlidingUpPanelLayout.removeView(panel.getPanelView());
                        mSlidingUpPanelLayout.addView(panel.getPanelView(), 1);

                        for (int i = 1; i < count; i++) {
                            BaseTaskPanel child = (BaseTaskPanel) mSlidingUpPanelLayout.getChildAt(i);
                            child.setFloor(count - i);
                        }
                        mSlidingUpPanelLayout.requestLayout();
                    }
                }

                int count = mSlidingUpPanelLayout.getChildCount();
                for (int i = 1; i < count; i++) {
                    ISlidingUpPanel panel2 = (ISlidingUpPanel) mSlidingUpPanelLayout.getChildAt(i);
                    if (panel2 == panel) {
                        panel2.getPanelView().setEnabled(false);
                    } else {
                        panel2.setSlideState(HIDDEN);
                        panel2.getPanelView().setEnabled(true);
                    }
                }
            }

            @Override
            public void onPanelCollapsed(ISlidingUpPanel panel) {
                int count = mSlidingUpPanelLayout.getChildCount();
                for (int i = 1; i < count; i++) {
                    panel = (ISlidingUpPanel) mSlidingUpPanelLayout.getChildAt(i);
                    panel.setSlideState(COLLAPSED);
                    panel.getPanelView().setEnabled(true);
                }
            }
        });
        mAdapter = new TaskAdapter(this, mTasks);
        mAdapter.setBasePanelListener(this);
        mSlidingUpPanelLayout.setAdapter(mAdapter);
    }

    private void initData() {
        mTasks = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Task task = new Task("你猜", "2017-8-2", "内容", "2017-8-5", "sender", "receiver", "topview" , i);
            mTasks.add(task);
        }
    }

    @Override
    protected TaskPresenter onCreatePresenter() {
        return new TaskPresenter(this, this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_task;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void addTask(View view){
        Log.d(TAG, "addTask: ");
    }

    public void next(View view){
        Log.d(TAG, "next: ");
        mTasks.clear();
        queryNewTask();
        mSlidingUpPanelLayout.setAdapter(mAdapter);
    }

    private void queryNewTask() {
        for (int i = 0; i < 3; i++) {
            Task task = new Task("你猜", "2017-8-2", "内容", "2017-8-5", "sender", "receiver", "topview" , i);
            mTasks.add(task);
        }

    }

    public void pre(View view){
        Log.d(TAG, "pre: ");
    }

    @Override
    public void clickBasePanel(int state) {
        if (state != EXPANDED){
            mSlidingUpPanelLayout.expandPanel();
        } else {
            mSlidingUpPanelLayout.collapsePanel();
        }
    }
}
