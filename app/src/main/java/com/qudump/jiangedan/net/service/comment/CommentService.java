package com.qudump.jiangedan.net.service.comment;

import com.qudump.jiangedan.net.bean.CommentNumberListRespBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dili on 2016/8/12.
 */
public interface CommentService {
    @GET("/api/threads/counts.json?")
    Observable<CommentNumberListRespBean> comments(@Query("threads") String params);
}
