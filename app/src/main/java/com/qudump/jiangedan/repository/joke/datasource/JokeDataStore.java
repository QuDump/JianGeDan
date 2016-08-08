package com.qudump.jiangedan.repository.joke.datasource;

import com.qudump.jiangedan.model.Joke;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public interface JokeDataStore {
    Observable<List<Joke>> jokes(int page);
}
