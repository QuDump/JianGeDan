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
    private CommentDataStoreFactory commentDataStoreFactory;
    private CommentDataStore commentDataStore;
    @Inject
    public CommentRepository(CommentDataStoreFactory commentDataStoreFactory) {
        this.commentDataStoreFactory = commentDataStoreFactory;
    }

    public Observable<List<Comment>> comments(long commentId) {
        commentDataStore = commentDataStoreFactory.createCloudDataStore();
        return commentDataStore.comments(commentId);
    }

    public Observable<List<Comment>> postComments(long id) {
        commentDataStore = commentDataStoreFactory.createCloudDataStore();
        return commentDataStore.postComments(id);
    }

    public Observable<String> like(long id){
        commentDataStore = commentDataStoreFactory.createCloudDataStore();
        return commentDataStore.like(id);
    }

    public Observable<String> dislike(long id){
        commentDataStore = commentDataStoreFactory.createCloudDataStore();
        return commentDataStore.dislike(id);
    }
}
