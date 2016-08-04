package com.qudump.jiangedan.cache;

import com.qudump.jiangedan.model.Post;

import rx.Observable;

/**
 * Created by dili on 2016/8/4.
 */
public class PostCacheImpl implements PostCache {
    @Override
    public Observable<Post> get(long id) {
        return null;
    }

    @Override
    public void put(Post post) {

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
