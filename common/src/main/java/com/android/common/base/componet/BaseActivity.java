package com.android.common.base.componet;

import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.common.R;
import com.android.common.base.mvp.BaseMvpPresenter;
import com.android.common.base.thread.ThreadPoolConst;
import com.android.common.base.thread.ThreadPoolManager;
import com.android.common.base.util.DialogFragmentHelper;
import com.android.common.base.util.HandleUtil;
import com.android.common.base.util.Toastor;
import com.android.common.manager.UserManager;
import com.android.common.push.InitPush;
import com.android.common.push.Notifications;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }


    //1.如何让调用者知道权限是否申请成功    回调
    //2.如果不是在Activity中调用，所需的上下文怎么处理   可以通过让所有的创建的Activity都
    //  存到一个集合当中，取其栈顶的Activity作为上下文
    private PermissionListener mListener;

    protected void requestRuntimePermission(String[] permission,
                                          PermissionListener permissionListener){
        //传过来的回调监听
        mListener = permissionListener;

        ArrayList<String> list = new ArrayList<>();
        for (String per:permission) {
            //如果当前的这个权限没有被申请
            if(ContextCompat.checkSelfPermission(this,per)!= PackageManager.PERMISSION_GRANTED){
                //添加到list集合当中
                list.add(per);
            }
        }

        //判断list集合是否为空，不为空则申请权限
        if(!list.isEmpty()){
            ActivityCompat.requestPermissions(this,list.toArray(new String[list.size()]),1);
        }else{
            //权限通过的回调方法
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //此处判断的是上方的标记“1”
            case 1:
                if(grantResults.length>0){
                    ArrayList<String> list=new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult=grantResults[i];
                        String permission=permissions[i];
                        if(grantResult!=PackageManager.PERMISSION_GRANTED){
                            list.add(permission);
                        }
                    }
                    //判断所有的权限是否都通过了
                    if(!list.isEmpty()){
                        mListener.onDenied(list);
                    }else{
                        mListener.onGranted();
                    }
                }
                break;
            default:
                break;
        }
    }

}
