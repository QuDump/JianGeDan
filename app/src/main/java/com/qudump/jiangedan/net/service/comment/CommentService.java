package com.qudump.jiangedan.net.service.comment;

import com.qudump.jiangedan.net.bean.BoringPicListRespBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dili on 2016/8/12.
 */
public interface CommentService {
    @GET("/api/threads/counts.json?")
    Call<BoringPicListRespBean> comments(@Query("threads") String params);
}
