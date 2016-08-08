package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.interactor.GetJokeList;
import com.qudump.jiangedan.model.Joke;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by dili on 2016/8/8.
 */
public class JokeListPresenter implements JokeListContract.Presenter {

    private GetJokeList getJokeList;
    private JokeListContract.View view;
    private boolean isRefresh = true;
    private List<Joke> mJokes = new ArrayList<>();

    @Inject
    public JokeListPresenter(GetJokeList getJokeList) {
        this.getJokeList = getJokeList;
    }

    @Override
    public void setView(JokeListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        loadRecent();
    }

    @Override
    public void destroy() {
        getJokeList.unsubscribe();
        view.stopRefresh();
    }

    @Override
    public void loadJokes(final int page) {
        isRefresh = (page == 1);
        getJokeList.setPage(page).execute(new GetJokesSubscriber());
    }

    @Override
    public void loadRecent() {
        loadJokes(1);
    }

    public class GetJokesSubscriber extends Subscriber<List<Joke>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Joke> jokes) {
            if(isRefresh) {
                mJokes.clear();
            }
            mJokes.addAll(jokes);
            view.renderView(mJokes);
            view.stopRefresh();
        }
    }
}
