package com.qudump.jiangedan.cache;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public interface BaseCache<T> {
    Observable<T> get(final long id);
    void put(T post);
    boolean isCached(final long id);
    boolean isExpired();
    void evictAll();
}
