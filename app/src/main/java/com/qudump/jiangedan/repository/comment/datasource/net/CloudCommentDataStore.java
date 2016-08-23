package com.qudump.jiangedan.repository.comment.datasource.net;

import com.qudump.jiangedan.model.Comment;
import com.qudump.jiangedan.net.bean.mapper.CommentBeanMapper;
import com.qudump.jiangedan.net.bean.mapper.DuoShuoCommentBeanMapper;
import com.qudump.jiangedan.net.service.comment.CommentApiService;
import com.qudump.jiangedan.repository.comment.datasource.CommentDataStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/15.
 */
public class CloudCommentDataStore implements CommentDataStore {
    private CommentApiService commentApiService;
    private DuoShuoCommentBeanMapper mapper;
    private CommentBeanMapper commentBeanMapper;

    @Inject
    public CloudCommentDataStore(CommentApiService commentApiService, DuoShuoCommentBeanMapper mapper, CommentBeanMapper commentBeanMapper) {
        this.commentApiService = commentApiService;
        this.mapper = mapper;
        this.commentBeanMapper = commentBeanMapper;
    }

    @Override
    public Observable<List<Comment>> comments(long commentId) {
        String params = "comment-"+commentId;
        return commentApiService.comments(params).map(mapper::transform);
    }

    @Override
    public Observable<List<Comment>> postComments(long id) {
        return commentApiService.postComments(id).map(commentBeanMapper::transform);
    }

    @Override
    public Observable<String> like(long id) {
        return commentApiService.like(id);
    }

    @Override
    public Observable<String> dislike(long id) {
        return commentApiService.dislike(id);
    }

    @Override
    public Observable<Boolean> writeComment(Comment comment) {
        Map<String,String> content = new HashMap<>();
        content.put("author_name",comment.getAuthorName());
        content.put("thread_id",comment.getThreadId());
        content.put("author_email",comment.getAuthorEmail());
        content.put("message",comment.getContent());
//        content.put("parent_id",comment.getParentId());
        content.put("parent_id",comment.getParentId()==null?"":comment.getParentId());

        return commentApiService.writeComment(content);
    }
}
