package com.qudump.jiangedan.net.service.joke;

import com.qudump.jiangedan.net.bean.JokeBean;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public class JokeApiServiceImpl implements JokeApiService {

    private Retrofit retrofit;

    @Inject
    public JokeApiServiceImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<List<JokeBean>> jokes(int page) {
        return retrofit
                .create(JokeService.class)
                .jokeList(page)
                .flatMap(resp->{
                    if(!resp.getStatus().equals("ok")) {
                        return Observable.error(new Throwable());
                    }

                    return Observable.just(resp.getComments());
                });
    }
}
