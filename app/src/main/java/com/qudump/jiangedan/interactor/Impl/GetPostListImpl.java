package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.GetPostList;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.PostRepository;

import rx.Observable;

/**
 * Created by dili on 2016/8/3.
 */
public class GetPostListImpl extends UseCase implements GetPostList {

    private PostRepository postRepository;

    public GetPostListImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, PostRepository postRepository) {
        super(threadExecutor, postExecutionThread);
        this.postRepository = postRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return ;
    }
}
