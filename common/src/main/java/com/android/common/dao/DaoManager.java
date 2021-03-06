package com.android.common.dao;

/**
 * Created by kiddo on 17-8-3.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.common.greendao.DaoMaster;
import com.android.common.greendao.DaoSession;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.concurrent.Callable;

/**
 *  greenDao管理类
 */
public abstract class DaoManager implements DaoService {

    private String dbName;
    private Context context;
    private OnSQLiteDatabaseListener mSqLiteDatabaseListener;
    private AbstractDaoMaster daoMaster;
    private DaoConfig mDaoConfig;
    private AbstractDaoSession daoSession;
    // 旧数据库版本，当用户进行升级的时候能够判断和保存升级之前的数据库版本
    // 当无进行升级操作则是当前数据库的版本
    private int mOldVersion;
    private int mDaoVersion;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;


    public DaoManager(Context context, String dbName, OnSQLiteDatabaseListener sqLiteDatabaseListener) {
        this.mSqLiteDatabaseListener = sqLiteDatabaseListener;
        this.context = context.getApplicationContext();
        this.dbName = dbName;
        init();
        AppDaoManager.getInstance().put(dbName, this);
    }

    public String getDbName() {
        return dbName;
    }

    public AbstractDaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(AbstractDaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public int getOldDaoVersion() {
        return mOldVersion;
    }

    public int getDaoVersion() {
        return mDaoVersion;
    }


    private void init() {
        mDaoConfig = new DaoConfig(this.context, this.dbName, mSqLiteDatabaseListener);
        this.daoMaster = initDaoMaster(mDaoConfig.openOrCreateDatabase());
        this.daoSession = daoMaster.newSession();
        //this.mOldVersion = daoMaster.getDatabase().getVersion();//由于2.0版本版本号可以存储在daosession中，可以获取，但是在3.0版本中做出改动，此处不再初始化版本号
        this.mDaoVersion = daoMaster.getSchemaVersion();
        //mDaoConfig.checkUpdate(daoSession, daoMaster.getDatabase().getVersion(), daoMaster.getSchemaVersion());
    }

    private void setDatabase(Context context) {

        // 通过DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为greenDAO 已经帮你做了。
        // 注意：默认的DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(context,"notes-db", null);
        db =mHelper.getWritableDatabase();
        // 注意：该数据库连接属于DaoMaster，所以多个 Session 指的是相同的数据库连接。
        //mDaoMaster = new DaoMaster(db);
        //mDaoSession = mDaoMaster.newSession();

    }

    /**
     * 升级数据库操作
     */
    public void checkUpdate() {
        try {
            //mDaoConfig.checkUpdate(daoSession, daoMaster.getDatabase().getVersion(), daoMaster.getSchemaVersion());
        } catch (Exception e) {
            Log.d("DaoManager", "手动升级数据库失败!!!!");
        }
    }

    /**
     * 创建相应new DaoMaster(daoHelper.getWritableDb());
     *
     * @param writableDb
     * @return
     */
    protected abstract AbstractDaoMaster initDaoMaster(SQLiteDatabase writableDb);

    public void close() {
        daoMaster.getDatabase().close();
        daoMaster = null;
        daoSession = null;
        mSqLiteDatabaseListener = null;
        mDaoConfig = null;
    }

    @Override
    public <T> void insert(T t) {
        daoSession.insertOrReplace(t);
    }

    @Override
    public <T> void update(T t) {
        daoSession.update(t);
    }

    @Override
    public <T> void delete(T t) {
        daoSession.delete(t);
    }

    @Override
    public <T> void deleteAll(Class<T> t) {
        daoSession.deleteAll(t);
    }

    @Override
    public <T> List<T> loadAll(Class<T> t) {
        return daoSession.loadAll(t);
    }

    @Override
    public <T> QueryBuilder<T> query(Class<T> t) {
        return (QueryBuilder<T>) daoSession.getDao(t).queryBuilder();
    }

    @Override
    public void runInTx(Runnable runnable) {
        daoSession.runInTx(runnable);
    }

    @Override
    public <V> V callInTx(Callable<V> callable) throws Exception {
        return daoSession.callInTx(callable);
    }
}