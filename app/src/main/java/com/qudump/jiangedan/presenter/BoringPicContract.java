package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.model.BoringPic;

import java.util.List;

/**
 * Created by qidong on 2016/8/8.
 */
public interface BoringPicContract {
    interface View extends BaseView<Presenter> {
        void renderView(List<BoringPic> boringPicList);
        void stopRefresh();
    }
    interface Presenter extends BasePresenter<View> {
        void loadRecent();
        void loadBoringPics(final int page);
    }
}
