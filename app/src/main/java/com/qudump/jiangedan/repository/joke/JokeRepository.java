package com.qudump.jiangedan.repository.joke;

import com.qudump.jiangedan.model.Joke;
import com.qudump.jiangedan.repository.joke.datasource.JokeDataStore;
import com.qudump.jiangedan.repository.joke.datasource.JokeDataStoreFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/3.
 */
public class JokeRepository {
    private JokeDataStoreFactory jokeDataStoreFactory;

    @Inject
    public JokeRepository(JokeDataStoreFactory jokeDataStoreFactory) {
        this.jokeDataStoreFactory = jokeDataStoreFactory;
    }

    public Observable<List<Joke>> jokes(int page){
        JokeDataStore jokeDataStore = jokeDataStoreFactory.createCloudDataStore();
        return jokeDataStore.jokes(page);
    }
}
