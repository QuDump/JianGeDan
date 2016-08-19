package com.qudump.jiangedan.injection.module;

import android.content.Context;

import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.PostExecutionThreadImpl;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.net.executor.ThreadExecutorImpl;
import com.qudump.jiangedan.net.interceptor.UserAgentInterceptor;
import com.qudump.jiangedan.net.retrofit.fastjsonconverter.FastJsonConverterFactory;
import com.qudump.jiangedan.ui.BaseApplication;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by dili on 2016/8/3.
 */
@Module
public class AppModule {
    private static final String BASE_URL = "http://i.jandan.net/";
    private BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }
    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return this.application.getApplicationContext();
    }

    @Provides @Singleton
    public ThreadExecutor provideThreadExecutor(ThreadExecutorImpl threadExecutor) {
        return threadExecutor;
    }

    @Provides @Singleton
    public PostExecutionThread providePostExecutionThread(PostExecutionThreadImpl postExecutionThread) {
        return postExecutionThread;
    }

    @Provides @Singleton
    public OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new UserAgentInterceptor("Jandan Android App V3.0.0.2"))
                .build();
    }

    @Provides @Singleton
    public Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides @Singleton
    public Retrofit.Builder provideRetrofitBuilder(OkHttpClient client){
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create());
    }
}
