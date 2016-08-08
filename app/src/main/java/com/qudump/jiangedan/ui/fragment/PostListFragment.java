package com.qudump.jiangedan.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.injection.module.PostListFragmentModule;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.presenter.PostListContract;
import com.qudump.jiangedan.presenter.PostListPresenter;
import com.qudump.jiangedan.ui.adapter.PostAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/3.
 */
public class PostListFragment extends AbstractBaseFragment implements PostListContract.View{

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    PostListPresenter presenter;

    private PostAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_list,container,false);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    @Override
    void initDagger() {
        getApplication()
                .buildPostComponent()
                .plus(new PostListFragmentModule())
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
        presenter.start();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden) {
            presenter.destroy();
        }
    }

    private void init(){
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadRecent();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext()));
        mAdapter = new PostAdapter(getActivityContext());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void renderView(List<Post> posts) {
        mAdapter.setData(posts);
    }

    @Override
    public void showErrMsg(String msg) {
        Toast.makeText(getActivityContext(),msg,Toast.LENGTH_SHORT);
    }

    @Override
    public void stopRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPresenter(PostListContract.Presenter presenter) {

    }
}
