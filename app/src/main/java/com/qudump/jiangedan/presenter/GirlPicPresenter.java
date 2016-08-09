package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.interactor.GetGirlPicList;
import com.qudump.jiangedan.model.GirlPic;

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
    private boolean isRefresh = true;
    private List<GirlPic> mGirlPics = new ArrayList<>();

    @Inject
    public GirlPicPresenter(GetGirlPicList getGirlPicList) {
        this.getGirlPicList = getGirlPicList;
    }

    @Override
    public void loadRecent() {
        loadGirlPics(1);
    }

    @Override
    public void loadGirlPics(int page) {
        isRefresh = (page ==1);
        getGirlPicList.setPage(page).execute(new GirlPicsSubscriber());

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
            if(isRefresh) {
                mGirlPics.clear();
            }
            mGirlPics.addAll(girlPics);
            view.renderView(mGirlPics);
            view.stopRefresh();
        }
    }
}