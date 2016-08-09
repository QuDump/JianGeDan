package com.qudump.jiangedan.cache;

import com.qudump.jiangedan.model.LittleVideo;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/9.
 */
public class VideoCache implements BaseCache<LittleVideo> {

    @Inject
    public VideoCache() {
    }

    @Override
    public Observable<LittleVideo> get(long id) {
        return null;
    }

    @Override
    public void put(LittleVideo post) {

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
