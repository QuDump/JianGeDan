package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.interactor.GetVideoList;
import com.qudump.jiangedan.model.LittleVideo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by dili on 2016/8/9.
 */
public class VideoListPresenter implements VideoListContract.Presenter {
    private GetVideoList getVideoList;
    private List<LittleVideo> mLittleVideos = new ArrayList<>();
    private boolean isRefresh = true;
    private VideoListContract.View view;

    @Inject
    public VideoListPresenter(GetVideoList getVideoList) {
        this.getVideoList = getVideoList;
    }

    @Override
    public void loadRecent() {
        loadVideos(1);
    }

    @Override
    public void loadVideos(int page) {
        isRefresh = (page == 1);
        getVideoList.setPage(page).execute(new VideoListSubscriber());
    }

    @Override
    public void setView(VideoListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        loadRecent();
    }

    @Override
    public void destroy() {
        getVideoList.unsubscribe();
    }

    public class VideoListSubscriber extends Subscriber<List<LittleVideo>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<LittleVideo> videos) {
            if(isRefresh) {
                mLittleVideos.clear();
            }
            mLittleVideos.addAll(videos);
            view.renderView(mLittleVideos);
            view.stopRefresh();
        }
    }
}
