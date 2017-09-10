package com.android.team.team.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.ui.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.common.base.componet.BaseMvpActivity;
import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.bean.Team;
import com.android.team.R;
import com.android.team.R2;
import com.android.team.team.CreateTeamEvent;
import com.android.team.team.create.CreateTeamActivity;
import com.android.team.team.util.GlideHelper;
import com.github.clans.fab.FloatingActionButton;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kiddo on 17-9-10.
 */

public class TeamDetailActivity extends BaseMvpActivity implements View.OnClickListener{
    private static final String TAG = "TeamDetailActivity";
    private FloatingActionButton writeFabFinish;
    private View listTouchInterceptor;
    private View detailsLayout;
    private UnfoldableView unfoldableView;
    private List<Team> teamList;


    @Override
    protected void init(BaseMvpPresenter presenter, Bundle savedInstanceState) {
        writeFabFinish = Views.find(this, R.id.write_fab_finish);
        writeFabFinish.setOnClickListener(this);
        ListView listView = Views.find(this, R.id.list_view);
        listView.setAdapter(new TeamDetailAdapter(getTeamList()));

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
                CreateTeamActivity.startActivity(this);
                break;
        }
    }
}
