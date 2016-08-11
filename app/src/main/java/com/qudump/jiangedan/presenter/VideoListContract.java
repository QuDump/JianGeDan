package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.model.LittleVideo;

import java.util.List;

/**
 * Created by dili on 2016/8/9.
 */
public interface VideoListContract {
    interface View extends BaseView {
        void renderView(List<LittleVideo> videos);
        void stopRefresh();
    }

    interface Presenter extends BasePresenter<View> {
        void loadRecent();
        void loadVideos(final int page);
        void loadNextPage();
    }
}
