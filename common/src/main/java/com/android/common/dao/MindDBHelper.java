package com.android.common.dao;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.common.greendao.DaoMaster;

import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;

/**
 * Created by kiddo on 17-8-3.
 */

public class MindDBHelper implements OnSQLiteDatabaseListener {
    private static final String TAG = "MindDBHelper";
    private volatile static MindDBHelper mInstance;

    public static synchronized MindDBHelper getInstance() {
        if (mInstance == null)
            mInstance = new MindDBHelper();
        return mInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate 创建数据库表");
        Database database = new StandardDatabase(db);
        DaoMaster.createAllTables(database, true);
    }

    @Override
    public void onUpgrade(AbstractDaoSession daoSession, int mOldVersion, int mNewVersion) {

    }
}
