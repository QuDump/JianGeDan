package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.interactor.GetPostDetail;
import com.qudump.jiangedan.model.Post;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by dili on 2016/8/5.
 */
public class PostDetailPresenter implements PostDetailContract.Presenter {

    private GetPostDetail getPostDetail;
    private PostDetailContract.View view;
    private Post mPost =new Post();

    @Inject
    public PostDetailPresenter(GetPostDetail getPostDetail) {
        this.getPostDetail = getPostDetail;
    }

    @Override
    public void loadPost(Post post) {
        mPost = post;
        getPostDetail.setPostId(post.getId()).execute(new PostDetailSubscriber());
    }

    @Override
    public void setView(PostDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void destory() {
        getPostDetail.unsubscribe();
    }

    public class PostDetailSubscriber extends Subscriber<Post> {

        @Override
        public void onCompleted() {
            view.showErrMsg("load complete");
        }

        @Override
        public void onError(Throwable e) {
            view.showErrMsg("network error");
        }

        @Override
        public void onNext(Post post) {
            mPost.setContent(post.getContent());
            view.renderView(mPost);
        }
    }
}
