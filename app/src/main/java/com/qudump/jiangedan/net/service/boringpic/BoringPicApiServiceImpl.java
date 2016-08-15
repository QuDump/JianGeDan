package com.qudump.jiangedan.net.service.boringpic;


import com.qudump.jiangedan.net.bean.BoringPicBean;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicApiServiceImpl implements BoringPicApiService {
    private Retrofit retrofit;

    @Inject
    public BoringPicApiServiceImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<List<BoringPicBean>> boringPics(int page) {

        return retrofit
                .create(BoringPicService.class)
                .boringPics(page)
                .flatMap(resp->{
                    if(!resp.getStatus().equals("ok")) {
                        return Observable.error(new Throwable());
                    }

                    return Observable.just(resp.getComments());
                });
    }
}
