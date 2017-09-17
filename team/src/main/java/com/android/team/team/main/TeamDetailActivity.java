package com.android.team.team.main;

import android.Manifest;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.ui.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.android.common.base.componet.BaseMvpActivity;
import com.android.common.base.componet.PermissionListener;
import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.bean.Team;
import com.android.common.manager.UserManager;
import com.android.common.push.InitPush;
import com.android.common.push.Notifications;
import com.android.team.R;
import com.android.team.team.CreateTeamEvent;
import com.android.team.team.create.CreateTeamActivity;
import com.android.team.team.invite.InviteActivity;
import com.android.team.team.util.GlideHelper;
import com.github.clans.fab.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiddo on 17-9-10.
 */

public class TeamDetailActivity extends BaseMvpActivity implements View.OnClickListener, PermissionListener{
    private static final String TAG = "TeamDetailActivity";
    private FloatingActionButton writeFabFinish;
    private View listTouchInterceptor;
    private View detailsLayout;
    private UnfoldableView unfoldableView;
    private List<Team> teamList;
    private TeamDetailAdapter mAdapter;


    @Override
    protected void init(BaseMvpPresenter presenter, Bundle savedInstanceState) {
        writeFabFinish = Views.find(this, R.id.write_fab_finish);
        writeFabFinish.setOnClickListener(this);
        ListView listView = Views.find(this, R.id.list_view);
        mAdapter = new TeamDetailAdapter(getTeamList());
        listView.setAdapter(mAdapter);

        listTouchInterceptor = Views.find(this, R.id.touch_interceptor_view);
        listTouchInterceptor.setClickable(false);

        detailsLayout = Views.find(this, R.id.details_layout);
        detailsLayout.setVisibility(View.INVISIBLE);

        unfoldableView = Views.find(this, R.id.unfoldable_view);

        unfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
                detailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
                detailsLayout.setVisibility(View.INVISIBLE);
            }
        });
        requestRuntimePermission(new String[]{Manifest.permission.READ_PHONE_STATE}, this);
        EventBus.getDefault().register(this);
    }

    private List<Team> getTeamList() {
        teamList = new ArrayList<>();
        teamList.add(new Team(R.drawable.starry_night, "Starry Night", "hello", "my and you"));
        teamList.add(new Team(R.drawable.starry_night, "Starry Night", "hello", "my and you"));
        teamList.add(new Team(R.drawable.starry_night, "Starry Night", "hello", "my and you"));
        teamList.add(new Team(R.drawable.starry_night, "Starry Night", "hello", "my and you"));
        return teamList;
    }

    @Override
    protected BaseMvpPresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_team_detail;
    }

    public void openDetails(View coverView, Team team) {
        final ImageView image = Views.find(detailsLayout, R.id.details_image);
        final TextView title = Views.find(detailsLayout, R.id.details_title);
        final TextView description = Views.find(detailsLayout, R.id.details_text);

        GlideHelper.loadPaintingImage(image, team);
        title.setText(team.getTeamName());

        SpannableBuilder builder = new SpannableBuilder(this);
        builder
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.description).append(": ")
                .clearStyle()
                .append(team.getDescription()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.group).append(": ")
                .clearStyle()
                .append(team.getTeamGroup());
        description.setText(builder.build());

        unfoldableView.unfold(coverView, detailsLayout);
    }

    @Override
    public void onBackPressed() {
        if (unfoldableView != null
                && (unfoldableView.isUnfolded() || unfoldableView.isUnfolding())) {
            unfoldableView.foldBack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.write_fab_finish:
                if (unfoldableView.isUnfolded()){
                    //添加团队成员
                    Log.d(TAG, "onClick: 添加成员");
                    InviteActivity.startActivity(this);
                } else {
                    CreateTeamActivity.startActivity(this);
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreateTeamEvent(CreateTeamEvent event) {
        Log.d(TAG, "onCreateTeamEvent: " + event.getTeam().toString());
        if (event.getTeam() != null){
            teamList.add(event.getTeam());
            mAdapter.notifyDataSetChanged();
        }
    }


    /**
     * test
     */

    private static final String IP = "http://192.168.199.133:30000";

    private void initPush(){
        Log.d(TAG, "initPush: ");
        Notifications.I.init(this.getApplicationContext());
        Notifications.I.setSmallIcon(com.android.common.R.drawable.fab_add);
        Notifications.I.setLargeIcon(BitmapFactory.decodeResource(getResources(), com.android.common.R.drawable.fab_add));
        String userName = UserManager.getInstance(this).getUserName();
        if (userName == null){
            userName = "kiddo";
        }
        InitPush.getInstance().initPush(this, IP, userName, getDeviceId());
        InitPush.getInstance().startPush(this);
    }

    private String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Activity.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            String time = Long.toString((System.currentTimeMillis() / (1000 * 60 * 60)));
            deviceId = time + time;
        }
        return deviceId;
    }

    @Override
    public void onGranted() {
        initPush();
    }

    @Override
    public void onDenied(ArrayList<String> list) {
        Toast.makeText(this, list.get(0) + "权限被拒绝,请到手机设置权限!", Toast.LENGTH_SHORT).show();
    }

    /**
     * test
     */
}
