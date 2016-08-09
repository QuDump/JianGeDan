package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.GetPostList;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.post.PostRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/3.
 */
public class GetPostListImpl extends UseCase<List<Post>> implements GetPostList {

    private PostRepository postRepository;
    private int page;

    @Inject
    public GetPostListImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, PostRepository postRepository) {
        super(threadExecutor, postExecutionThread);
        this.postRepository = postRepository;
    }

    @Override
    protected Observable<List<Post>> buildUseCaseObservable() {

        return postRepository.posts(page);
    }

    @Override
    public GetPostList setPage(int page) {
        this.page = page;
        return this;
    }
}
