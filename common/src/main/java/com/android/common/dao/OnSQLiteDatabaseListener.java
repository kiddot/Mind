package com.android.common.dao;

/**
 * Created by kiddo on 17-8-3.
 */

import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.AbstractDaoSession;

/**
 * 数据库监听器
 */
public interface OnSQLiteDatabaseListener {
    /**
     * 在这里创造数据库表，通过Greendao生成的DaoMaster创造
     * @param db
     */
    void onCreate(SQLiteDatabase db);

    /**
     * 在这里通过daoSession进行更新操作
     * @param daoSession
     * @param mOldVersion
     * @param mNewVersion
     */
    void onUpgrade(AbstractDaoSession daoSession, int mOldVersion, int mNewVersion);
}
