package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.model.GirlPic;

import java.util.List;

/**
 * Created by dili on 2016/8/8.
 */
public interface GirlPicContract {
    interface View extends BaseView<Presenter>{
        void renderView(List<GirlPic> girlPics);
        void stopRefresh();
    }

    interface Presenter extends BasePresenter<View> {
        void loadRecent();
        void loadGirlPics(int page);
        void loadNextPage();
    }
}
