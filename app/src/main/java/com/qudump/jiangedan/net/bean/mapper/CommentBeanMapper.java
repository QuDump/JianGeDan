package com.qudump.jiangedan.net.bean.mapper;

import com.qudump.jiangedan.model.Comment;
import com.qudump.jiangedan.net.bean.CommentBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            comment.setAuthorName(bean.getName().trim());
            comment.setIndex(bean.getIndex());
            comment.setDate(bean.getDate());
            comment.setContent(trimWebTag(bean.getContent().replace("\\n","").replace("\\r","").trim()));
            comment.setLike(bean.getVote_positive());
            comment.setDislike(bean.getVote_negative());
        }

        return comment;
    }

    public String trimWebTag(String content) {
        String regExTag = "<[^>]+>";
        Pattern tagPattern = Pattern.compile(regExTag);
        Matcher tagMatcher = tagPattern.matcher(content);
        return tagMatcher.replaceAll("");
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
