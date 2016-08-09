package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.interactor.GetBoringPicList;
import com.qudump.jiangedan.model.BoringPic;

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
    private boolean isRefresh = true;
    private List<BoringPic> mBoringPics = new ArrayList<>();

    @Inject
    public BoringPicListPresenter(GetBoringPicList getBoringPicList) {
        this.getBoringPicList = getBoringPicList;
    }

    @Inject

    @Override
    public void loadRecent() {
        loadBoringPics(1);
    }

    @Override
    public void loadBoringPics(int page) {
        isRefresh = (page == 1);
        getBoringPicList.setPage(page).execute(new GetBoringPicsSubscriber());
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
            if(isRefresh){
                mBoringPics.clear();
            }
            mBoringPics.addAll(boringPics);
            view.renderView(mBoringPics);
            view.stopRefresh();
        }
    }
}
