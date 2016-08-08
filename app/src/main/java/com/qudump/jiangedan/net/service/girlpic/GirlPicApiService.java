package com.qudump.jiangedan.net.service.girlpic;

import com.qudump.jiangedan.net.bean.GirlPicBean;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public interface GirlPicApiService {
    Observable<List<GirlPicBean>> girlPics(int page);
}
