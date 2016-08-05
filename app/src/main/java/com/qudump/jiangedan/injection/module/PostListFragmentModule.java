package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.interactor.GetPostList;
import com.qudump.jiangedan.interactor.Impl.GetPostListImpl;
import com.qudump.jiangedan.presenter.PostListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qidong on 2016/8/4.
 */
@Module
public class PostListFragmentModule {

    @Provides
    GetPostList provideGetPostList(GetPostListImpl getPostList){
        return getPostList;
    }

    @Provides
    PostListPresenter providesPostListPresenter(GetPostList getPostList){
        return new PostListPresenter(getPostList);
    }
}
