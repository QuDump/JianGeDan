package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.GetPostComments;
import com.qudump.jiangedan.model.Comment;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.comment.CommentRepository;
import com.qudump.jiangedan.repository.post.PostRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/11.
 */
public class GetPostCommentsImpl extends UseCase<List<Comment>> implements GetPostComments {

    private CommentRepository commentRepository;

    @Inject
    public GetPostCommentsImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CommentRepository commentRepository) {
        super(threadExecutor, postExecutionThread);
        this.commentRepository = commentRepository;
    }

    private long id;
    @Override
    public GetPostComments setId(long id) {
        this.id = id;
        return this;
    }

    @Override
    protected Observable<List<Comment>> buildUseCaseObservable() {
        return commentRepository.postComments(id);
    }
}
