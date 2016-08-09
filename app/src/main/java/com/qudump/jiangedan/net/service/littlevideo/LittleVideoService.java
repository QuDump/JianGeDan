package com.qudump.jiangedan.net.service.littlevideo;

import com.qudump.jiangedan.net.bean.VideoListRespBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dili on 2016/8/9.
 */
public interface LittleVideoService {
    @GET("/?oxwlxojflwblxbsapi=jandan.get_video_comments")
    Observable<VideoListRespBean> videoList(@Query("page") int page);
}
