package com.qudump.jiangedan.net.service.girlpic;

import com.qudump.jiangedan.net.bean.AttitudeReqBean;
import com.qudump.jiangedan.net.bean.GirlListRespBean;
import com.qudump.jiangedan.net.bean.GirlPicBean;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public interface GirlPicService {
    @GET("/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments")
    Observable<GirlListRespBean> girlPics(@Query("page") int page);
}
