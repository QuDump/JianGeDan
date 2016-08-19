package com.qudump.jiangedan.net.service.comment;

import com.qudump.jiangedan.net.bean.BaseResp;
import com.qudump.jiangedan.net.bean.CommentBean;
import com.qudump.jiangedan.net.bean.CommentNumberBean;
import com.qudump.jiangedan.net.bean.DuoshuoCommentBean;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/12.
 */
public interface CommentApiService {
    Observable<List<CommentNumberBean>> commentNumbers(String params);
    Observable<List<DuoshuoCommentBean>> comments(String param);
    Observable<List<CommentBean>> postComments(long id);
    Observable<String> like(long id);
    Observable<String> dislike(long id);
}
