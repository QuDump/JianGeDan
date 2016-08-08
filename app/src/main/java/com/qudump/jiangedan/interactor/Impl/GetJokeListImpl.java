package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.GetJokeList;
import com.qudump.jiangedan.model.Joke;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.joke.JokeRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public class GetJokeListImpl extends UseCase implements GetJokeList {

    private JokeRepository jokeRepository;
    private int page;
    @Inject
    public GetJokeListImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, JokeRepository jokeRepository) {
        super(threadExecutor, postExecutionThread);
        this.jokeRepository = jokeRepository;
    }

    @Override
    public GetJokeList setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    protected Observable<List<Joke>> buildUseCaseObservable() {
        return jokeRepository.jokes(page);
    }
}
