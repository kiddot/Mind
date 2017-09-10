package com.android.team.team.util;

import android.widget.ImageView;

import com.android.common.bean.Team;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by kiddo on 17-9-10.
 */

public class GlideHelper {

    private GlideHelper() {}

    public static void loadPaintingImage(ImageView image, Team painting) {
        Glide.with(image.getContext().getApplicationContext())
                .load(painting.getImageId())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image);
    }

}
