package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.PostComment;
import com.qudump.jiangedan.model.Comment;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.comment.CommentRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dell on 2016/8/22.
 */
public class PostCommentImpl extends UseCase<Boolean> implements PostComment{

    private Comment comment;
    private CommentRepository repository;

    @Inject
    public PostCommentImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CommentRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public PostComment setComment(Comment comment) {
        this.comment = comment;
        return this;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable() {
        return repository.writeComment(comment);
    }
}
