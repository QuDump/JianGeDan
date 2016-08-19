package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.event.AttitudeEvent;
import com.qudump.jiangedan.event.EVENT_SOURCE;
import com.qudump.jiangedan.interactor.GetVideoList;
import com.qudump.jiangedan.interactor.PostAttitude;
import com.qudump.jiangedan.model.LittleVideo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by dili on 2016/8/9.
 */
public class VideoListPresenter implements VideoListContract.Presenter {
    private GetVideoList getVideoList;
    private PostAttitude postAttitude;
    private List<LittleVideo> mLittleVideos = new ArrayList<>();
    private VideoListContract.View view;
    private int currentPage = 1;

    @Inject
    public VideoListPresenter(GetVideoList getVideoList, PostAttitude postAttitude) {
        this.getVideoList = getVideoList;
        this.postAttitude = postAttitude;
        EventBus.getDefault().register(this);
    }

    @Override
    public void loadRecent() {
        currentPage = 1;
        loadVideos(currentPage);
    }

    @Override
    public void loadVideos(int page) {
        getVideoList.setPage(page).execute(new VideoListSubscriber());
    }

    @Override
    public void loadNextPage() {
        loadVideos(currentPage++);
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
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onLikeEvent(AttitudeEvent event){
        if(event.getSource() == EVENT_SOURCE.SOURCE_GIRL_PIC) {
            postAttitude.setId(event.getId()).setAttitude(event.isLike()).execute(new AttitudeSubscriber());
        }
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
            if(currentPage == 1) {
                mLittleVideos.clear();
            }
            mLittleVideos.addAll(videos);
            view.renderView(mLittleVideos);
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
