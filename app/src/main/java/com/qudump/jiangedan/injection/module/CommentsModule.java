package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.interactor.Impl.PostAttitudeImpl;
import com.qudump.jiangedan.interactor.PostAttitude;
import com.qudump.jiangedan.net.service.comment.CommentApiService;
import com.qudump.jiangedan.net.service.comment.CommentApiServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dili on 2016/8/15.
 */
@Module
public class CommentsModule {
    @Provides
    CommentApiService providesCommentApiService(CommentApiServiceImpl commentApiService){
        return  commentApiService;
    }

    @Provides
    PostAttitude providesPostAttitude(PostAttitudeImpl postAttitude){
        return postAttitude;
    }
}
