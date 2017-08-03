package com.android.common.base.mvp;

import android.content.Context;
import android.support.annotation.CallSuper;

import com.android.common.base.thread.ThreadPoolConst;
import com.android.common.base.thread.ThreadPoolManager;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

/**
 * Created by kiddo on 17-8-3.
 */

public class BaseMvpPresenter<V extends BaseMvpView> implements Presenter<V> {
    private  V mvpView;
    private WeakReference<V> mView;
    private Context mContext;

    protected static Executor sHTTPExecutor = ThreadPoolManager.getInstance().getThreadPool(ThreadPoolConst.THREAD_TYPE_SIMPLE_HTTP);
    protected static Executor sWORKExecutor = ThreadPoolManager.getInstance().getThreadPool(ThreadPoolConst.THREAD_TYPE_WORK);


    public BaseMvpPresenter(Context context, V view) {
        attachView(context, view);
    }

    @Override
    public void attachView(Context context, V mvpView) {
        mView = new WeakReference<V>(mvpView);
        mContext = context.getApplicationContext();
        onAttachView();
    }

    protected void onAttachView() {
    }

    @Override
    public void detachView() {
        onDetachView();
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    /**
     * 关闭一些资源或请求
     * 如activity退出后结束所有正在进行的网络请求
     */
    protected void onDetachView() {
    }

    /**
     * 判断 view是否为空
     * @return
     */
    public  boolean isAttachView(){
        return mvpView != null;
    }
    /**
     * 返回目标view
     * @return
     */
    public V getView() {
        V view;
        if (mView != null && (view = mView.get()) != null)
            return view;

        if (mvpView == null)
            mvpView = getDefaultView();
        return mvpView;
    }

    protected V getDefaultView() {
        return null;
    }
    /**
     * 检查view和presenter是否连接
     */
    public void checkViewAttach(){
        if(! isAttachView()){
            throw  new MvpViewNotAttachedException();
        }
    }
    /**
     * 自定义异常
     */
    public static   class  MvpViewNotAttachedException extends RuntimeException{
        public  MvpViewNotAttachedException(){
            super("请求数据前请先调用 attachView(MvpView) 方法与View建立连接");
        }
    }

    public void onCreate() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }
}
