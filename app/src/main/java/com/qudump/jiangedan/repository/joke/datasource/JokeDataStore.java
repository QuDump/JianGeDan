package com.qudump.jiangedan.repository.joke.datasource;

import com.qudump.jiangedan.model.Joke;
import com.qudump.jiangedan.net.bean.CommentNumberBean;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public interface JokeDataStore {
    Observable<List<Joke>> jokes(int page);
    Observable<List<CommentNumberBean>> commentNumbers(String params);
}
