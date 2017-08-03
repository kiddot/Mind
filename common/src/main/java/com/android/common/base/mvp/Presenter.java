package com.android.common.base.mvp;

import android.content.Context;

/**
 * Created by kiddo on 17-8-3.
 */

public interface Presenter<V extends BaseMvpView> {
    /**
     * presenter和对应的view绑定
     * @param mvpView  目标view
     */
    void attachView(Context context, V mvpView);
    /**
     * presenter与view解绑
     */
    void detachView();
}
