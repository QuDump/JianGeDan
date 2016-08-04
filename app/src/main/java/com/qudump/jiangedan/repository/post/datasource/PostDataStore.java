package com.qudump.jiangedan.repository.post.datasource;

import com.qudump.jiangedan.model.Post;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/4.
 */
public interface PostDataStore {
    Observable<List<Post>> postList(int page);
    Observable<Post> postDetail(long id);
}
