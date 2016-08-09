package com.qudump.jiangedan.net.service.littlevideo;

import com.qudump.jiangedan.net.bean.LittleVideoBean;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by dili on 2016/8/9.
 */
public class LittleVideoApiServiceImpl implements LittleVideoApiService {

    private Retrofit retrofit;

    @Inject
    public LittleVideoApiServiceImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<List<LittleVideoBean>> videoList(int page) {
        return retrofit
                .create(LittleVideoService.class)
                .videoList(page)
                .flatMap(resp -> {
                    if(!resp.getStatus().equals("ok")) {
                        return Observable.error(new Throwable());
                    }

                    return Observable.just(resp.getComments());
                });
    }
}
