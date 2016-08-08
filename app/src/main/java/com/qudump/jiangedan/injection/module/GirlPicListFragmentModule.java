package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.interactor.GetGirlPicList;
import com.qudump.jiangedan.interactor.Impl.GetGirlPicListImpl;
import com.qudump.jiangedan.presenter.GirlPicPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dili on 2016/8/8.
 */
@Module
public class GirlPicListFragmentModule {
    @Provides
    GetGirlPicList providesGetGirlPicList(GetGirlPicListImpl getGirlPicList) {
        return getGirlPicList;
    }
    @Provides
    GirlPicPresenter providesGirlPicPresenter(GetGirlPicList getGirlPicList) {
        return new GirlPicPresenter(getGirlPicList);
    }
}
