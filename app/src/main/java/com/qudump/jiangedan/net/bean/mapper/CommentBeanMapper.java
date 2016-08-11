package com.qudump.jiangedan.net.bean.mapper;

import com.qudump.jiangedan.model.Comment;
import com.qudump.jiangedan.net.bean.CommentBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dili on 2016/8/11.
 */
public class CommentBeanMapper {

    @Inject
    public CommentBeanMapper() {
    }

    public Comment transform(CommentBean bean) {
        Comment comment=null;
        if(null != bean) {
            comment = new Comment();
            comment.setId(bean.getId());
            comment.setAuthorName(bean.getName());
            comment.setIndex(bean.getIndex());
            comment.setDate(bean.getDate());
            comment.setContent(bean.getContent());
            comment.setLike(bean.getVote_positive());
            comment.setDislike(bean.getVote_negative());
        }

        return comment;
    }

    public List<Comment> transform(List<CommentBean> commentBeanList) {
        List<Comment> comments = new ArrayList<>();
        if(null != commentBeanList) {
            for(CommentBean bean:commentBeanList) {
                Comment comment = transform(bean);
                if(null != comment) {
                    comments.add(comment);
                }
            }
        }

        return comments;
    }
}
