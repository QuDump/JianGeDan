package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.event.EVENT_SOURCE;
import com.qudump.jiangedan.event.AttitudeEvent;
import com.qudump.jiangedan.interactor.GetGirlPicList;
import com.qudump.jiangedan.interactor.PostAttitude;
import com.qudump.jiangedan.model.GirlPic;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by dili on 2016/8/8.
 */
public class GirlPicPresenter implements GirlPicContract.Presenter {

    private GirlPicContract.View view;
    private GetGirlPicList getGirlPicList;
    private PostAttitude postAttitude;
    private boolean isRefresh = true;
    private List<GirlPic> mGirlPics = new ArrayList<>();
    private int currentPage = 1;

    @Inject
    public GirlPicPresenter(GetGirlPicList getGirlPicList, PostAttitude postAttitude) {
        this.getGirlPicList = getGirlPicList;
        this.postAttitude = postAttitude;
        EventBus.getDefault().register(this);
    }

    @Override
    public void loadRecent() {
        currentPage = 1;
        loadGirlPics(currentPage);
    }

    @Override
    public void loadGirlPics(int page) {
        getGirlPicList.setPage(page).execute(new GirlPicsSubscriber());

    }

    @Override
    public void loadNextPage() {
        loadGirlPics(++currentPage);
    }

    @Override
    public void setView(GirlPicContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        loadRecent();
    }

    @Override
    public void destroy() {
        getGirlPicList.unsubscribe();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onLikeEvent(AttitudeEvent event){
        if(event.getSource() == EVENT_SOURCE.SOURCE_GIRL_PIC) {
            postAttitude.setId(event.getId()).setAttitude(event.isLike()).execute(new AttitudeSubscriber());
        }
    }

    public class GirlPicsSubscriber extends Subscriber<List<GirlPic>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<GirlPic> girlPics) {
            if(currentPage == 1) {
                mGirlPics.clear();
            }
            mGirlPics.addAll(girlPics);
            view.renderView(mGirlPics);
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
