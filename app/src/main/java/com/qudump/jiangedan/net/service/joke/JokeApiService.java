package com.qudump.jiangedan.net.service.joke;

import com.qudump.jiangedan.net.bean.JokeBean;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public interface JokeApiService {

    Observable<List<JokeBean>> jokes(int page);
}
