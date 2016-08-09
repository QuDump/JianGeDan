package com.qudump.jiangedan.net.service.littlevideo;

import com.qudump.jiangedan.net.bean.LittleVideoBean;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/9.
 */
public interface LittleVideoApiService {
    Observable<List<LittleVideoBean>> videoList(int page);
}
