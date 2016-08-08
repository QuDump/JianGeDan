package com.qudump.jiangedan.net.service.boringpic;

import com.qudump.jiangedan.net.bean.BoringPicBean;

import java.util.List;

import rx.Observable;

/**
 * Created by qidong on 2016/8/8.
 */
public interface BoringPicApiService {
    Observable<List<BoringPicBean>> boringPics(int page);
}
