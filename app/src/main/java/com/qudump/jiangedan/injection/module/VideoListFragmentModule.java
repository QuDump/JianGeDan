package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.interactor.GetVideoList;
import com.qudump.jiangedan.interactor.Impl.GetVideoListImpl;
import com.qudump.jiangedan.presenter.VideoListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dili on 2016/8/9.
 */
@Module
public class VideoListFragmentModule {

    @Provides
    GetVideoList providesGetVideoList(GetVideoListImpl getVideoList) {
        return getVideoList;
    }

    @Provides
    VideoListPresenter providesVideoListPresenter(GetVideoList getVideoList) {
        return new VideoListPresenter(getVideoList);
    }
}
