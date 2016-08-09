package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.GetPostDetail;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.post.PostRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/5.
 */
public class GetPostDetailImpl extends UseCase<Post> implements GetPostDetail {

    private long id;
    private PostRepository postRepository;

    @Inject
    public GetPostDetailImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, PostRepository postRepository) {
        super(threadExecutor, postExecutionThread);
        this.postRepository = postRepository;
    }

    @Override
    public GetPostDetail setPostId(long id) {
        this.id = id;
        return this;
    }

    @Override
    protected Observable<Post> buildUseCaseObservable() {
        return postRepository.post(id);
    }
}
