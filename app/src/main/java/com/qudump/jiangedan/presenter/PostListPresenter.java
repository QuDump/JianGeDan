package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.interactor.GetPostList;
import com.qudump.jiangedan.model.Post;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by dili on 2016/8/3.
 */
public class PostListPresenter implements PostListContract.Presenter {

    private GetPostList getPostList;
    private PostListContract.View view;
    private boolean isRefresh = true;
    private List<Post> mPosts = new ArrayList<>();
    private int currentPage = 1;

    @Inject
    public PostListPresenter(GetPostList getPostList) {
        this.getPostList = getPostList;
    }

    @Override
    public void start() {
        loadRecent();
    }

    @Override
    public void destroy() {
        getPostList.unsubscribe();
    }

    @Override
    public void loadPosts(int page) {
        getPostList
                .setPage(page)
                .execute(new PostListSubscriber());
    }

    @Override
    public void loadRecent() {
        currentPage = 1;
        loadPosts(currentPage);
    }

    @Override
    public void loadNextPage() {
        loadPosts(++currentPage);
    }

    @Override
    public void setView(PostListContract.View view) {
        this.view = view;
    }

    public class PostListSubscriber extends Subscriber<List<Post>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Post> posts) {
            if(currentPage == 1) {
                mPosts.clear();
            }
            mPosts.addAll(posts);
            view.renderView(mPosts);
            view.stopRefresh();
        }
    }
}
