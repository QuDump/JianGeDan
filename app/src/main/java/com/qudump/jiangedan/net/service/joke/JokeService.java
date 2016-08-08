package com.qudump.jiangedan.net.service.joke;

import com.qudump.jiangedan.net.bean.BaseResp;
import com.qudump.jiangedan.net.bean.JokeListRespBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public interface JokeService {
    @GET("/?oxwlxojflwblxbsapi=jandan.get_duan_comments")
    Observable<JokeListRespBean> jokeList(@Query("page") int page);

    @POST("/index.php?acv_ajax=true")
    Observable<BaseResp> attitude(@Query("option") int result);

    @GET("/api/threads/listPosts.json")
    Observable<BaseResp> commentList(@Query("thread_key") int result);

    @POST("/api/posts/create.json")
    Observable<BaseResp> postComment();

}
