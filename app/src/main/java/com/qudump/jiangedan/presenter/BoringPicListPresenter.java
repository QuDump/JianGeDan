package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.event.AttitudeEvent;
import com.qudump.jiangedan.event.EVENT_SOURCE;
import com.qudump.jiangedan.interactor.GetBoringPicList;
import com.qudump.jiangedan.interactor.PostAttitude;
import com.qudump.jiangedan.model.BoringPic;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicListPresenter implements BoringPicContract.Presenter {
    private BoringPicContract.View view;
    private GetBoringPicList getBoringPicList;
    private PostAttitude postAttitude;
    private List<BoringPic> mBoringPics = new ArrayList<>();
    private int currentPage = 1;

    @Inject
    public BoringPicListPresenter(GetBoringPicList getBoringPicList, PostAttitude postAttitude) {
        this.getBoringPicList = getBoringPicList;
        this.postAttitude = postAttitude;
        EventBus.getDefault().register(this);
    }

    @Override
    public void loadRecent() {
        currentPage = 1;
        loadBoringPics(currentPage);
    }

    @Override
    public void loadBoringPics(int page) {
        getBoringPicList.setPage(page).execute(new GetBoringPicsSubscriber());
    }

    @Override
    public void loadNextPage() {
        loadBoringPics(++currentPage);
    }

    @Override
    public void setView(BoringPicContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        loadRecent();
    }

    @Override
    public void destroy() {
        getBoringPicList.unsubscribe();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onLikeEvent(AttitudeEvent event){
        if(event.getSource() == EVENT_SOURCE.SOURCE_GIRL_PIC) {
            postAttitude.setId(event.getId()).setAttitude(event.isLike()).execute(new AttitudeSubscriber());
        }
    }

    public class GetBoringPicsSubscriber extends Subscriber<List<BoringPic>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<BoringPic> boringPics) {
            if(currentPage == 1){
                mBoringPics.clear();
            }
            mBoringPics.addAll(boringPics);
            view.renderView(mBoringPics);
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
