package com.qudump.jiangedan.net.service.girlpic;

import com.qudump.jiangedan.net.bean.GirlPicBean;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public class GirlPicApiServiceImpl implements GirlPicApiService {

    private Retrofit retrofit;

    @Inject
    public GirlPicApiServiceImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<List<GirlPicBean>> girlPics(int page) {
        return retrofit
                .create(GirlPicService.class)
                .girlPics(page)
                .flatMap(resp->{
                    if(!resp.getStatus().equals("ok")) {
                        return Observable.error(new Throwable());
                    }

                    return Observable.just(resp.getComments());
                });
    }
}
