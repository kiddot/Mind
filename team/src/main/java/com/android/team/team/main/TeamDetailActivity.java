package com.android.team.team.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.alexvasilkov.foldablelayout.shading.GlanceFoldShading;
import com.android.common.base.componet.BaseMvpActivity;
import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.team.R;
import com.android.team.R2;

import butterknife.BindView;

/**
 * Created by kiddo on 17-9-10.
 */

public class TeamDetailActivity extends BaseMvpActivity {
    @BindView(R2.id.list_view)
    ListView listView;
    @BindView(R2.id.touch_interceptor_view)
    View touchInterceptorView;
    @BindView(R2.id.details_layout)
    LinearLayout detailsLayout;
    @BindView(R2.id.unfoldable_view)
    UnfoldableView unfoldableView;

    private View listTouchInterceptor;

    @Override
    protected void init(BaseMvpPresenter presenter, Bundle savedInstanceState) {
        listView.setAdapter(new TeamDetailAdapter(this));

        listTouchInterceptor.setClickable(false);

        detailsLayout.setVisibility(View.INVISIBLE);

        Bitmap glance = BitmapFactory.decodeResource(getResources(), R.drawable.unfold_glance);
        unfoldableView.setFoldShading(new GlanceFoldShading(glance));

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

    @Override
    protected BaseMvpPresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_team_detail;
    }

}
