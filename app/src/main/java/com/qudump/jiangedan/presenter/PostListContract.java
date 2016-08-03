package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.model.Post;

import java.util.List;

/**
 * Created by dili on 2016/8/3.
 */
public interface PostListContract {
    interface View extends BaseView<Presenter>{
        void renderView(List<Post> posts);
        void stopRefresh();
    }
    interface Presenter extends BasePresenter<View>{
        void loadPosts(int page);
        void loadRecent();
    }
}
