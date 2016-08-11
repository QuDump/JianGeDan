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
    private List<Joke> mJokes = new ArrayList<>();
    private int currentPage = 1;

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
        getJokeList.setPage(page).execute(new GetJokesSubscriber());
    }

    @Override
    public void loadRecent() {
        currentPage = 1;
        loadJokes(currentPage);
    }

    @Override
    public void loadNextPage() {
        loadJokes(currentPage++);
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
            if(currentPage == 1) {
                mJokes.clear();
            }
            mJokes.addAll(jokes);
            view.renderView(mJokes);
            view.stopRefresh();
        }
    }
}
