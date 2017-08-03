package com.android.common.dao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by kiddo on 17-8-3.
 */

public interface DaoService {

    <T> void insert(T t);

    <T> void update(T t);

    <T> void delete(T t);

    <T> void deleteAll(Class<T> t);

    <T> List<T> loadAll(Class<T> t);

    <T> QueryBuilder<T> query(Class<T> t);

    /**
     * 在事务中,运行runnable操作
     *
     * @param runnable
     */
    void runInTx(Runnable runnable);

    /**
     * 在事务中，运行callable操作
     *
     * @param callable
     * @param <V>
     * @return
     */
    <V> V callInTx(Callable<V> callable) throws Exception;
}
