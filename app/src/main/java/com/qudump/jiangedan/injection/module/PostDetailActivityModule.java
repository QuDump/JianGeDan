package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.interactor.GetPostDetail;
import com.qudump.jiangedan.interactor.Impl.GetPostDetailImpl;
import com.qudump.jiangedan.presenter.PostDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qidong on 2016/8/4.
 */
@Module
public class PostDetailActivityModule {

    @Provides
    GetPostDetail provideGetPostDetail(GetPostDetailImpl getPostDetail){
        return getPostDetail;
    }

    @Provides
    PostDetailPresenter providesPostDetailPresenter(GetPostDetail getPostDetail) {
        return new PostDetailPresenter(getPostDetail);
    }
}
