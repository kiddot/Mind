package com.android.team.team.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.ui.Views;
import com.android.common.bean.Task;
import com.android.common.bean.Team;
import com.android.team.R;

/**
 * Created by kiddo on 17-9-10.
 */

public class TeamDetailAdapter extends ItemsAdapter<Team, TeamDetailAdapter.ViewHolder>
        implements View.OnClickListener{

    public TeamDetailAdapter(Context context) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindHolder(ViewHolder viewHolder, int position) {

    }

    static class ViewHolder extends ItemsAdapter.ViewHolder {
        final ImageView image;
        final TextView title;

        ViewHolder(ViewGroup parent) {
            super(Views.inflate(parent, R.layout.list_item));
            image = Views.find(itemView, R.id.list_item_image);
            title = Views.find(itemView, R.id.list_item_title);
        }
    }

}
