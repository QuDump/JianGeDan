package com.qudump.jiangedan.cache;

import com.qudump.jiangedan.model.GirlPic;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public class GirlPicCache implements BaseCache<GirlPic> {

    @Inject
    public GirlPicCache() {
    }

    @Override
    public Observable<GirlPic> get(long id) {
        return null;
    }

    @Override
    public void put(GirlPic post) {

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
