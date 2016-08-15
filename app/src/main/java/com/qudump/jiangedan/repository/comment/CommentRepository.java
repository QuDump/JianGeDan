package com.qudump.jiangedan.repository.comment;

import com.qudump.jiangedan.model.Comment;
import com.qudump.jiangedan.repository.comment.datasource.CommentDataStore;
import com.qudump.jiangedan.repository.comment.datasource.CommentDataStoreFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/15.
 */
public class CommentRepository {
    CommentDataStoreFactory commentDataStoreFactory;

    @Inject
    public CommentRepository(CommentDataStoreFactory commentDataStoreFactory) {
        this.commentDataStoreFactory = commentDataStoreFactory;
    }

    public Observable<List<Comment>> comments(long commentId) {
        CommentDataStore commentDataStore = commentDataStoreFactory.createCloudDataStore();
        return commentDataStore.comments(commentId);
    }

    public Observable<List<Comment>> postComments(long id) {
        CommentDataStore commentDataStore = commentDataStoreFactory.createCloudDataStore();
        return commentDataStore.postComments(id);
    }
}
