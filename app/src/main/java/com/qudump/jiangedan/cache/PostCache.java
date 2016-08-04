package com.qudump.jiangedan.cache;

import com.qudump.jiangedan.model.Post;

import rx.Observable;

/**
 * Created by dili on 2016/8/4.
 */
public interface PostCache {
    Observable<Post> get(final long id);
    void put(Post post);
    boolean isCached(final long id);
    boolean isExpired();
    void evictAll();
}
