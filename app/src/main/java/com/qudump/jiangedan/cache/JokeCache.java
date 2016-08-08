package com.qudump.jiangedan.cache;

import com.qudump.jiangedan.model.Joke;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public class JokeCache implements BaseCache<Joke> {

    @Inject
    public JokeCache() {
    }

    @Override
    public Observable<Joke> get(long id) {
        return null;
    }

    @Override
    public void put(Joke post) {

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
