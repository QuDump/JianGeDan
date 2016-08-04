package com.qudump.jiangedan.net.service.post;

import com.qudump.jiangedan.net.bean.PostBean;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/4.
 */
public interface PostApiService {
    String BASE_URL = "";

    Observable<List<PostBean>> postList(int page);
    Observable<PostBean> postDetailById(long id);
}
