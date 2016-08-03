package com.qudump.jiangedan.injection.component;

import android.content.Context;

import com.qudump.jiangedan.injection.module.AppModule;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by dili on 2016/8/3.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    Retrofit retrofit();
}
