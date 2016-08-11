package com.qudump.jiangedan.net.service.post;

import com.qudump.jiangedan.net.bean.PostDetailRespBean;
import com.qudump.jiangedan.net.bean.PostListRespBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dili on 2016/8/3.
 */
public interface PostService {
    @GET("/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1")
    Observable<PostListRespBean> getPosts(@Query("page") String page);

    @GET("/?oxwlxojflwblxbsapi=get_post&include=content")
    Observable<PostDetailRespBean> getPostDetail(@Query("id") long id);

    @GET("/?oxwlxojflwblxbsapi=get_post&include=comments")
    Observable<PostDetailRespBean> getPostComments( @Query("id") long id);

    @POST("/")
    Observable<PostDetailRespBean> postAttitude();

    @POST("/")
    Observable<PostDetailRespBean> postComment();
}
