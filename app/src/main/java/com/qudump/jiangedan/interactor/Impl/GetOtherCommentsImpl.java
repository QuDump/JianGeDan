package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.GetOtherComments;
import com.qudump.jiangedan.model.Comment;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.comment.CommentRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/15.
 */
public class GetOtherCommentsImpl extends UseCase<List<Comment>> implements GetOtherComments {

    private CommentRepository commentRepository;
    private long id;

    @Inject
    public GetOtherCommentsImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CommentRepository commentRepository) {
        super(threadExecutor, postExecutionThread);
        this.commentRepository = commentRepository;
    }

    @Override
    public GetOtherComments setId(long id) {
        this.id = id;
        return this;
    }

    @Override
    protected Observable<List<Comment>> buildUseCaseObservable() {
        return commentRepository.comments(id);
    }
}
