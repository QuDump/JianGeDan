package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.AppModule;
import com.qudump.jiangedan.injection.module.BoringPicModule;
import com.qudump.jiangedan.injection.module.GirlPicModule;
import com.qudump.jiangedan.injection.module.JokeModule;
import com.qudump.jiangedan.injection.module.PostModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dili on 2016/8/3.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    PostComponent plus(PostModule postModule);
    JokeComponent plus(JokeModule jokeModule);
    GirlPicComponent plus(GirlPicModule girlPicModule);
    BoringPicComponent plus(BoringPicModule boringPicModule);
}
