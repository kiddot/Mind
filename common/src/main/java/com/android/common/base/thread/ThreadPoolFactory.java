package com.android.common.base.thread;

import com.android.common.base.util.SingletonFactory;

public class ThreadPoolFactory {

    public static ThreadPoolInterface getThreadPoolManager(){
        return SingletonFactory.getInstance(ThreadPoolManager.class);
    }
}
