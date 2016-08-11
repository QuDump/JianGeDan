package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.model.Comment;

import java.util.List;

/**
 * Created by dili on 2016/8/11.
 */
public interface PostCommentListContract {
    interface View extends BaseView {
        void renderView(List<Comment> commentList);
    }

    interface Presenter extends BasePresenter<View> {
        void loadComments(long id);
    }
}
