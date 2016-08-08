package com.qudump.jiangedan.cache;

import com.qudump.jiangedan.model.BoringPic;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicCache implements BaseCache<BoringPic> {
    @Inject
    public BoringPicCache() {
    }

    @Override
    public Observable<BoringPic> get(long id) {
        return null;
    }

    @Override
    public void put(BoringPic post) {

    }

    @Override
    public boolean isCached(long id) {
        return false;
    }

    @Override
    public boolean isExpired() {
        return true;
    }

    @Override
    public void evictAll() {

    }
}
