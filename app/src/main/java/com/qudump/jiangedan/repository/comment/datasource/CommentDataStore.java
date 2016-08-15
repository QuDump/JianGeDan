package com.qudump.jiangedan.repository.comment.datasource;

import com.qudump.jiangedan.model.Comment;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/15.
 */
public interface CommentDataStore {
    Observable<List<Comment>> comments(long commentId);
    Observable<List<Comment>> postComments(long id);
}