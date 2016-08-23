package com.qudump.jiangedan.net.service.comment;

import com.qudump.jiangedan.net.bean.CommentNumberListRespBean;
import com.qudump.jiangedan.net.bean.DuoshuoCommentsRespBean;
import com.qudump.jiangedan.net.bean.PostDetailRespBean;
import com.qudump.jiangedan.net.bean.WriteCommentRespBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dili on 2016/8/12.
 */
public interface CommentService {
    @GET("http://jandan.duoshuo.com/api/threads/counts.json?")
    Observable<CommentNumberListRespBean> commentNumbers(@Query("threads") String params);

    @GET("http://jandan.duoshuo.com/api/threads/listPosts.json?")
    Observable<DuoshuoCommentsRespBean> comments(@Query("thread_key") String params);

    @FormUrlEncoded
    @POST("http://jandan.duoshuo.com/api/posts/create.json")
    Observable<WriteCommentRespBean> writeComment(@FieldMap Map<String,String> content);

    @FormUrlEncoded
    @POST("/index.php?acv_ajax=true&option=1")
    Observable<Response<ResponseBody>> like(@Field("ID") long id);

    @FormUrlEncoded
    @POST("/index.php?acv_ajax=true&option=0")
    Observable<Response<ResponseBody>> dislike(@Field("ID") long id);

    @GET("/?oxwlxojflwblxbsapi=get_post&include=comments")
    Observable<PostDetailRespBean> getPostComments(@Query("id") long id);

}
