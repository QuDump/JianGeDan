package com.qudump.jiangedan.repository.joke.datasource.net;

import com.qudump.jiangedan.cache.JokeCache;
import com.qudump.jiangedan.model.Joke;
import com.qudump.jiangedan.net.bean.CommentNumberRespBean;
import com.qudump.jiangedan.net.bean.mapper.JokeBeanMapper;
import com.qudump.jiangedan.net.service.comment.CommentApiService;
import com.qudump.jiangedan.net.service.joke.JokeApiService;
import com.qudump.jiangedan.repository.joke.datasource.JokeDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dili on 2016/8/8.
 */
public class CloudJokeDataStore implements JokeDataStore {

    private JokeApiService apiService;
    private CommentApiService commentApiService;
    private JokeCache jokeCache;
    private JokeBeanMapper jokeBeanMapper;
    private Action1<Joke> saveToCacheAction = joke -> {
        if (joke != null) {
            jokeCache.put(joke);
        }
    };

    @Inject
    public CloudJokeDataStore(JokeApiService apiService, CommentApiService commentApiService, JokeCache jokeCache, JokeBeanMapper jokeBeanMapper) {
        this.apiService = apiService;
        this.commentApiService = commentApiService;
        this.jokeCache = jokeCache;
        this.jokeBeanMapper = jokeBeanMapper;
    }

    @Override
    public Observable<List<Joke>> jokes(int page) {
        return apiService.jokes(page).map(jokeBeanMapper::transform);
    }

    @Override
    public Observable<List<CommentNumberRespBean>> commentNumbers(String params) {
        return commentApiService.commentNumbers(params);
    }
}
