package com.android.common.base.componet;

import android.os.Bundle;
import android.support.annotation.CallSuper;

import com.android.common.base.mvp.BaseMvpPresenter;

/**
 * Created by kiddo on 17-8-3.
 */

public abstract class BaseMvpActivity<P extends BaseMvpPresenter> extends BaseActivity {
    private static final String TAG = "BaseMvpActivity";

    protected P mPresenter;

    @Override
    protected void init(Bundle savedInstanceState) {
        mPresenter = onCreatePresenter();
        init(mPresenter, savedInstanceState);
    }

    abstract protected void init(P presenter, Bundle savedInstanceState);

    /**
     * 创建present
     *
     * @return present
     */
    protected abstract P onCreatePresenter();

    final public P getPresenter() {
        return mPresenter;
    }


    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.onResume();
    }

    @Override
    @CallSuper
    protected void onStop() {
        super.onStop();
        if (mPresenter != null)
            mPresenter.onStop();
    }

    @Override
    @CallSuper
    protected void onPause() {
        super.onPause();
        if (mPresenter != null)
            mPresenter.onPause();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.onDestroy();
        }
    }
}
