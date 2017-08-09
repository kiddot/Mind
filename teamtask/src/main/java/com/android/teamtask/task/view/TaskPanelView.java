package com.android.teamtask.task.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.common.bean.Task;
import com.android.teamtask.R;
import com.xw.repo.supl.ISlidingUpPanel;
import com.xw.repo.supl.SlidingUpPanelLayout;

/**
 * Created by kiddo on 17-8-8.
 */
public class TaskPanelView extends BaseTaskPanel implements View.OnClickListener {

    private View mContentLayout;
    private View mMenuLayout;
    private View mExpendLayout;
    private ImageView mCollapseImg;
    private ImageView mSettingsImg;
    private TextView mTitleText;
    private View mCollapseLayout;
    private TextView mSender;
    private TextView mBarTitle;
    private TextView mBarTime;
    private ImageView mBarIcon;
    private TextView mTvContent;

    private int mWeatherTypeCode = 1;

    public TaskPanelView(Context context) {
        this(context, null);
    }

    public TaskPanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaskPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.part_task_content, this, true);
        mContentLayout = findViewById(R.id.panel_content_layout);
        mMenuLayout = findViewById(R.id.panel_menu_layout);
        mExpendLayout = findViewById(R.id.panel_expend_layout);
        mCollapseImg = (ImageView) findViewById(R.id.panel_collapse_img);
        mSettingsImg = (ImageView) findViewById(R.id.panel_settings_img);
        mTitleText = (TextView) findViewById(R.id.panel_tv_title);
        mTvContent = (TextView) findViewById(R.id.panel_tv_content);
        mCollapseLayout = findViewById(R.id.panel_collapse_layout);
        mSender = (TextView) findViewById(R.id.panel_tv_bar_sender);
        mBarTitle = (TextView) findViewById(R.id.panel_tv_bar_title);
        mBarTime = (TextView) findViewById(R.id.panel_tv_bar_time);
        mBarIcon = (ImageView) findViewById(R.id.panel_iv_bar_icon);
        mCollapseImg.setOnClickListener(this);
        mSettingsImg.setOnClickListener(this);

        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = getResources().getDimensionPixelSize(resId);
        mMenuLayout.setPadding(0, statusBarHeight, 0, 0);
        mExpendLayout.setPadding(0, statusBarHeight, 0, 0);

        checkVisibilityOfViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.panel_collapse_img:
                if (mCollapseImg.getAlpha() == 1) {
                    ((SlidingUpPanelLayout) getParent()).collapsePanel();
                }

                break;
            case R.id.panel_settings_img:
                if (mSettingsImg.getAlpha() >= 1) {
                    Toast.makeText(getContext(), "settings", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void setSlideState(@SlidingUpPanelLayout.SlideState int slideState) {
        super.setSlideState(slideState);

        checkVisibilityOfViews();
    }

    @Override
    public void onSliding(@NonNull ISlidingUpPanel panel, int top, int dy, float slidedProgress) {
        super.onSliding(panel, top, dy, slidedProgress);

        if (dy < 0) { // 向上
            float radius = getRadius();
            if (radius > 0 && MAX_RADIUS >= top) {
                setRadius(top);
            }

            float alpha = mCollapseLayout.getAlpha();
            if (alpha > 0f && top < 200) {
                alpha += dy / 200.0f;
                mCollapseLayout.setAlpha(alpha < 0 ? 0 : alpha); // 逐隐
            }

            alpha = mMenuLayout.getAlpha();
            if (alpha < 1f && top < 100) {
                alpha -= dy / 100.0f;
                mMenuLayout.setAlpha(alpha > 1 ? 1 : alpha); // 逐显
            }

            alpha = mExpendLayout.getAlpha();
            if (alpha < 1f) {
                alpha -= dy / 1000.0f;
                mExpendLayout.setAlpha(alpha > 1 ? 1 : alpha); // 逐显
            }
        } else { // 向下
            float radius = getRadius();
            if (radius < MAX_RADIUS) {
                radius += top;
                setRadius(radius > MAX_RADIUS ? MAX_RADIUS : radius);
            }

            float alpha = mCollapseLayout.getAlpha();
            if (alpha < 1f) {
                alpha += dy / 800.0f;
                mCollapseLayout.setAlpha(alpha > 1 ? 1 : alpha); // 逐显
            }

            alpha = mMenuLayout.getAlpha();
            if (alpha > 0f) {
                alpha -= dy / 100.0f;
                mMenuLayout.setAlpha(alpha < 0 ? 0 : alpha); // 逐隐
            }

            alpha = mExpendLayout.getAlpha();
            if (alpha > 0f) {
                alpha -= dy / 1000.0f;
                mExpendLayout.setAlpha(alpha < 0 ? 0 : alpha); // 逐隐
            }
        }
    }

    @Override
    public void setTaskModel(Task task) {
        mTask = task;
        if (task == null)
            return;

        mTitleText.setText(task.getTitle());
        mBarTime.setText(task.getStartDate());
        mTvContent.setText(task.getContent());
        mSender.setText(task.getSender());
        mBarTitle.setText(task.getTitle());

        checkVisibilityOfViews();
    }

    private void checkVisibilityOfViews() {
        if (mWeatherTypeCode == 1) {
            mContentLayout.setBackgroundColor(Color.parseColor("#80DEEA"));
        } else if (mWeatherTypeCode == 2) {
            mContentLayout.setBackgroundColor(Color.parseColor("#78909C"));
        } else {
            mContentLayout.setBackgroundColor(Color.parseColor("#03A9F4"));
        }

        if (mSlideState == SlidingUpPanelLayout.COLLAPSED) {
            setRadius(MAX_RADIUS);

            mMenuLayout.setAlpha(0f);
            mExpendLayout.setAlpha(0f);
            mCollapseLayout.setAlpha(1f);
        } else if (mSlideState == SlidingUpPanelLayout.EXPANDED) {
            setRadius(0);

            mMenuLayout.setAlpha(1f);
            mExpendLayout.setAlpha(1f);
            mCollapseLayout.setAlpha(0f);
        }
    }
}
