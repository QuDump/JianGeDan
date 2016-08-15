package com.qudump.jiangedan.repository.joke.datasource.cache;

import com.qudump.jiangedan.cache.JokeCache;
import com.qudump.jiangedan.model.Joke;
import com.qudump.jiangedan.net.bean.CommentNumberBean;
import com.qudump.jiangedan.repository.joke.datasource.JokeDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public class DiskJokeDataStore implements JokeDataStore {
    private JokeCache jokeCache;

    @Inject
    public DiskJokeDataStore(JokeCache jokeCache) {
        this.jokeCache = jokeCache;
    }

    @Override
    public Observable<List<Joke>> jokes(int page) {
        return null;
    }

    @Override
    public Observable<List<CommentNumberBean>> commentNumbers(String params) {
        return null;
    }
}
