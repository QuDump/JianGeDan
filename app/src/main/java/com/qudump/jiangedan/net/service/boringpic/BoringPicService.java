package com.qudump.jiangedan.net.service.boringpic;

import com.qudump.jiangedan.net.bean.BoringPicListRespBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by qidong on 2016/8/8.
 */
public interface BoringPicService {
    @GET("/?oxwlxojflwblxbsapi=jandan.get_pic_comments")
    Observable<BoringPicListRespBean> boringPics(@Query("page") int page);
}
