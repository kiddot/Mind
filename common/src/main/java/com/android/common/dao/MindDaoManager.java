package com.android.common.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.common.greendao.DaoMaster;

import org.greenrobot.greendao.AbstractDaoMaster;

/**
 * Created by kiddo on 17-8-3.
 */

public class MindDaoManager extends DaoManager{

    public MindDaoManager(Context context, String dbName, OnSQLiteDatabaseListener sqLiteDatabaseListener) {
        super(context, dbName, sqLiteDatabaseListener);
    }

    @Override
    protected AbstractDaoMaster initDaoMaster(SQLiteDatabase writableDb) {
        return new DaoMaster(writableDb);
    }
}