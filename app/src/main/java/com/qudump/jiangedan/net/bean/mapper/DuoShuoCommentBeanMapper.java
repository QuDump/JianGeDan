package com.qudump.jiangedan.net.bean.mapper;

import com.qudump.jiangedan.model.Comment;
import com.qudump.jiangedan.net.bean.DuoshuoCommentBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dili on 2016/8/15.
 */
public class DuoShuoCommentBeanMapper {

    @Inject
    public DuoShuoCommentBeanMapper() {
    }

    public Comment transform(DuoshuoCommentBean bean) {
        Comment comment = null;
        if(null != bean) {
            comment = new Comment();
            comment.setDate(bean.getCreated_at());
            comment.setDislike(bean.getDislike());
            comment.setLike(bean.getLike());
            comment.setPostId(bean.getPost_id());
            comment.setAuthorName(bean.getAuthor().getName());
            comment.setAvatarUrl(bean.getAuthor().getAvatar_url());
            comment.setHotComment(bean.isHotPost());
            comment.setContent(bean.getMessage());
            comment.setParentId(bean.getParent_id());

        }

        return comment;
    }

    public List<Comment> transform(List<DuoshuoCommentBean> commentBeanList) {
        List<Comment> comments = new ArrayList<>();
        for(DuoshuoCommentBean bean:commentBeanList) {
            Comment comment = transform(bean);
            if(null != comment) {
                comments.add(comment);
            }
        }

        return comments;
    }
}
