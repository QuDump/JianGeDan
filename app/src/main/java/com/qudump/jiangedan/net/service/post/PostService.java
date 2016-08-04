package com.qudump.jiangedan.net.service.post;

import com.qudump.jiangedan.net.bean.PostDetailRespBean;
import com.qudump.jiangedan.net.bean.PostListRespBean;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

import retrofit2.http.GET;

/**
 * Created by dili on 2016/8/3.
 */
public interface PostService {
    @GET("")
    Observable<PostListRespBean> getPosts(@QueryMap Map<String,String> options);

    @GET("")
    Observable<PostDetailRespBean> getPostDetail(@Query("oxwlxojflwblxbsapi") String api, @Query("id") long id,@Query("include") String content);

    @GET("")
    Observable<PostDetailRespBean> getPostComments(@Query("oxwlxojflwblxbsapi") String api, @Query("id") long id,@Query("include") String content);

    @POST("")
    Observable<PostDetailRespBean> postAttitude();

    @POST("")
    Observable<PostDetailRespBean> postComment();
}
