package com.qudump.jiangedan.net.service.post;

import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.net.bean.CommentBean;
import com.qudump.jiangedan.net.bean.PostBean;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/4.
 */
public interface PostApiService {
    Observable<List<PostBean>> postList(int page);
    Observable<Post> postDetailById(long id);
    Observable<List<CommentBean>> postComments(long id);
}
