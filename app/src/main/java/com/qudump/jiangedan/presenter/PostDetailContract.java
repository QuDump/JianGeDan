package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.model.Post;

/**
 * Created by dili on 2016/8/5.
 */
public interface PostDetailContract {
    interface View extends BaseView {
        void renderView(Post post);
        void stopRefresh();
    }
    interface Presenter extends BasePresenter<View>{
        void loadPost(Post post);
    }
}
