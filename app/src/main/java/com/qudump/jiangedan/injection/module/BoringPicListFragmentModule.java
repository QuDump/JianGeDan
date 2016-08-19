package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.interactor.GetBoringPicList;
import com.qudump.jiangedan.interactor.Impl.GetBoringPicListImpl;
import com.qudump.jiangedan.interactor.PostAttitude;
import com.qudump.jiangedan.presenter.BoringPicListPresenter;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qidong on 2016/8/8.
 */
@Module
public class BoringPicListFragmentModule {
    @Provides
    GetBoringPicList providesGetBoringPicList(GetBoringPicListImpl getBoringPicList) {
        return getBoringPicList;
    }

    @Provides
    BoringPicListPresenter providesBoringPicListPresenter(GetBoringPicList getBoringPicList, PostAttitude postAttitude) {
        return new BoringPicListPresenter(getBoringPicList,postAttitude);
    }
}
