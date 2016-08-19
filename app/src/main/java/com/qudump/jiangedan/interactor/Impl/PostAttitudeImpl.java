package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.PostAttitude;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.comment.CommentRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dell on 2016/8/18.
 */
public class PostAttitudeImpl extends UseCase<String> implements PostAttitude {

    private CommentRepository repository;
    private long id;
    private boolean isLike;

    @Inject
    public PostAttitudeImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CommentRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public PostAttitude setId(long id) {
        this.id = id;
        return this;
    }

    @Override
    public PostAttitude setAttitude(boolean isLike) {
        this.isLike = isLike;
        return this;
    }

    @Override
    protected Observable<String> buildUseCaseObservable() {
        if(isLike){
            return repository.like(id);
        } else {
            return repository.dislike(id);
        }
    }
}
