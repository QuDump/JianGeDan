package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.interactor.GetPostComments;
import com.qudump.jiangedan.model.Comment;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by dili on 2016/8/11.
 */
public class PostCommentListPresenter implements PostCommentListContract.Presenter {

    private GetPostComments getPostComments;
    private PostCommentListContract.View view;

    @Inject
    public PostCommentListPresenter(GetPostComments getPostComments) {
        this.getPostComments = getPostComments;
    }

    @Override
    public void loadComments(long id) {
        getPostComments.setId(id).execute(new GetCommentsSubscriber());
    }

    @Override
    public void setView(PostCommentListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        getPostComments.unsubscribe();
    }

    public class GetCommentsSubscriber extends Subscriber<List<Comment>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Comment> commentList) {
            view.renderView(commentList);
            if(null == commentList || 0 == commentList.size()) {
                view.showErrMsg("暂时还没有评论哦");
            }

        }
    }
}
