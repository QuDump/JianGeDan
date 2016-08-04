package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.interactor.GetPostList;

import javax.inject.Inject;

/**
 * Created by dili on 2016/8/3.
 */
public class PostListPresenter implements PostListContract.Presenter {

    private GetPostList getPostList;

    public PostListPresenter(GetPostList getPostList) {
        this.getPostList = getPostList;
    }

    @Override
    public void start() {

    }

    @Override
    public void destory() {

    }

    @Override
    public void loadPosts(int page) {

    }

    @Override
    public void loadRecent() {

    }

    @Override
    public void setView(PostListContract.View view) {

    }
}
