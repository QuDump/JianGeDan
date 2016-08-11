package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.model.Joke;

import java.util.List;

/**
 * Created by dili on 2016/8/8.
 */
public interface JokeListContract {
    interface View extends BaseView {
        void renderView(List<Joke> jokes);
        void stopRefresh();
    }

    interface Presenter extends BasePresenter<View> {
        void loadJokes(int page);
        void loadRecent();
        void loadNextPage();
    }
}
