package com.android.common.dao;

import android.content.Context;

import com.android.common.base.util.Check;
import com.android.common.manager.UserManager;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by kiddo on 17-9-17.
 */

public class CommonDao {
    public static <T> List<T> query(Context context, Class<T> clazz, WhereCondition... whereConditions) {
        return generateQueryBuilder(context, clazz, whereConditions).list();
    }

    public static <T> long queryCount(Context context, Class<T> clazz, WhereCondition... whereConditions) {
        return generateQueryBuilder(context, clazz, whereConditions).count();
    }

    public static <T> QueryBuilder generateQueryBuilder(Context context, Class<T> clazz, WhereCondition... whereConditions) {
        DaoManager manager = UserManager.getInstance(context).getDaoManager();
        QueryBuilder<T> builder = manager.query(clazz);
        if (whereConditions.length != 0)
            for (WhereCondition condition : whereConditions) {
                if (condition != null)
                    builder.where(condition);
            }
        return builder;
    }

    public static <T> void insert(Context context, final List<T> list) {
        if (!Check.isEmpty(list)) {
            final DaoManager manager = UserManager.getInstance(context).getDaoManager();
            manager.runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T t : list) {
                        manager.insert(t);
                    }
                }
            });
        }
    }

    public static <T> void insert(Context context, T t) {
        DaoManager manager = UserManager.getInstance(context).getDaoManager();
        manager.insert(t);
    }


    public static <T> void update(Context context, final List<T> list) {
        if (!Check.isEmpty(list)) {
            final DaoManager manager = UserManager.getInstance(context).getDaoManager();
            manager.runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T t : list) {
                        manager.update(t);
                    }
                }
            });
        }
    }

    public static <T> void delete(Context context, T t) {
        DaoManager manager = UserManager.getInstance(context).getDaoManager();
        manager.delete(t);
    }
}
