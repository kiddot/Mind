package com.android.common.base.componet;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.base.thread.ThreadPoolConst;
import com.android.common.base.thread.ThreadPoolManager;
import com.android.common.base.util.DialogFragmentHelper;
import com.android.common.base.util.HandleUtil;
import com.android.common.base.util.Toastor;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;

import static com.android.common.BaseApplication.getContext;


public abstract class BaseActivity<P extends BaseMvpPresenter> extends AppCompatActivity {

    //以ClassName作为TAG
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected static Executor sHTTPExecutor = ThreadPoolManager.getInstance().getThreadPool(ThreadPoolConst.THREAD_TYPE_SIMPLE_HTTP);
    protected static Executor sWORKExecutor = ThreadPoolManager.getInstance().getThreadPool(ThreadPoolConst.THREAD_TYPE_WORK);
    private Toastor mToast;
    private DialogFragment mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        final int sdk = Build.VERSION.SDK_INT;
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        if (sdk >= Build.VERSION_CODES.KITKAT) {
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            // 设置透明状态栏
            if ((params.flags & bits) == 0) {
                params.flags |= bits;
                window.setAttributes(params);
            }
        }
        setContentView(getContentViewId());
        //setContentView(getLayoutId());
        //ButterKnife.bind(this);
        if (mToast == null && getContext() != null) mToast = new Toastor(getContext());
        init(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    /*
    * 重写该方法来设置布局Id
    */
    protected abstract int getContentViewId();

    /*
    * 初始化
    * */
    protected abstract void init(Bundle savedInstanceState);

    protected void showToast(final String content) {
        HandleUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (getContext() == null) return;
                if (mToast == null) mToast = new Toastor(getContext());
                mToast.showToast(content);
            }
        });
    }

    protected void showLoading(String msg) {
        boolean isDestroy;
        if (Build.VERSION.SDK_INT > 16) {
            isDestroy = isDestroyed();
        } else {
            isDestroy = isFinishing();
        }
        if (mLoadingDialog == null && !isDestroy)
            mLoadingDialog = DialogFragmentHelper.showProgress(getSupportFragmentManager(), msg, true);
    }

    protected void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
