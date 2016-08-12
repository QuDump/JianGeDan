package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.net.service.boringpic.BoringPicApiService;
import com.qudump.jiangedan.net.service.boringpic.BoringPicApiServiceImpl;
import com.qudump.jiangedan.net.service.comment.CommentApiService;
import com.qudump.jiangedan.net.service.comment.CommentApiServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qidong on 2016/8/8.
 */
@Module
public class BoringPicModule {

    @Provides
    BoringPicApiService providesBoringPicApiService(BoringPicApiServiceImpl boringPicApiService) {
        return boringPicApiService;
    }

    @Provides
    CommentApiService providesCommentApiService(CommentApiServiceImpl commentApiService){
        return  commentApiService;
    }
}
