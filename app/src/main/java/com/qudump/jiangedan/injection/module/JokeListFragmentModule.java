package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.interactor.GetJokeList;
import com.qudump.jiangedan.interactor.Impl.GetJokeListImpl;
import com.qudump.jiangedan.interactor.PostAttitude;
import com.qudump.jiangedan.presenter.JokeListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dili on 2016/8/8.
 */
@Module
public class JokeListFragmentModule {

    @Provides
    GetJokeList providesGetJokeList(GetJokeListImpl getJokeList) {
        return getJokeList;
    }

    @Provides
    JokeListPresenter providesJokeListPresenter(GetJokeList getJokeList, PostAttitude postAttitude) {
        return new JokeListPresenter(getJokeList,postAttitude);
    }
}
