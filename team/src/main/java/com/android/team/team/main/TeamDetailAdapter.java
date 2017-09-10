package com.android.team.team.main;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.ui.ContextHelper;
import com.alexvasilkov.android.commons.ui.Views;
import com.android.common.bean.Task;
import com.android.common.bean.Team;
import com.android.team.R;
import com.android.team.team.util.GlideHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by kiddo on 17-9-10.
 */

public class TeamDetailAdapter extends ItemsAdapter<Team, TeamDetailAdapter.ViewHolder>
        implements View.OnClickListener{

    public TeamDetailAdapter(List<Team> list) {
        setItemsList(list);
    }

    @Override
    public void onClick(View view) {
        final Team item = (Team) view.getTag(R.id.list_item_image);
        final Activity activity = ContextHelper.asActivity(view.getContext());

        if (activity instanceof TeamDetailActivity) {
            ((TeamDetailActivity) activity).openDetails(view, item);
        }
    }

    @Override
    protected ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = new ViewHolder(parent);
        holder.image.setOnClickListener(this);
        return holder;
    }

    @Override
    protected void onBindHolder(ViewHolder holder, int position) {
        final Team item = getItem(position);

        holder.image.setTag(R.id.list_item_image, item);
        GlideHelper.loadPaintingImage(holder.image, item);
        holder.title.setText(item.getTeamName());
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
