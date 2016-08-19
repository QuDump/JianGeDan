package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.event.AttitudeEvent;
import com.qudump.jiangedan.event.EVENT_SOURCE;
import com.qudump.jiangedan.interactor.GetJokeList;
import com.qudump.jiangedan.interactor.PostAttitude;
import com.qudump.jiangedan.model.Joke;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by dili on 2016/8/8.
 */
public class JokeListPresenter implements JokeListContract.Presenter {

    private GetJokeList getJokeList;
    private PostAttitude postAttitude;
    private JokeListContract.View view;
    private List<Joke> mJokes = new ArrayList<>();
    private int currentPage = 1;

    @Inject
    public JokeListPresenter(GetJokeList getJokeList, PostAttitude postAttitude) {
        this.getJokeList = getJokeList;
        this.postAttitude = postAttitude;
        EventBus.getDefault().register(this);
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
        EventBus.getDefault().unregister(this);
        view.stopRefresh();
    }

    @Subscribe
    public void onLikeEvent(AttitudeEvent event){
        if(event.getSource() == EVENT_SOURCE.SOURCE_GIRL_PIC) {
            postAttitude.setId(event.getId()).setAttitude(event.isLike()).execute(new AttitudeSubscriber());
        }
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
        loadJokes(++currentPage);
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

    public class AttitudeSubscriber extends Subscriber<String> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String result) {

        }
    }
}
