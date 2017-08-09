package com.android.common.base.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by kiddo on 17-8-9.
 */

public class Dp2Px {
    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
