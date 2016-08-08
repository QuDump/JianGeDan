package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.net.service.post.PostApiService;
import com.qudump.jiangedan.net.service.post.PostApiServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qidong on 2016/8/4.
 */
@Module
public class PostModule {

    @Provides
    PostApiService providePostApiService(PostApiServiceImpl postApiService){
        return postApiService;
    }

}
