package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.net.service.joke.JokeApiService;
import com.qudump.jiangedan.net.service.joke.JokeApiServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dili on 2016/8/8.
 */
@Module
public class JokeModule {
    @Provides
    JokeApiService providesJokeApiService(JokeApiServiceImpl jokeApiService) {
        return jokeApiService;
    }
}
