package com.qudump.jiangedan.interactor;

import com.qudump.jiangedan.model.Comment;

/**
 * Created by dell on 2016/8/22.
 */
public interface PostComment extends Interactor{
    PostComment setComment(Comment comment);
}
