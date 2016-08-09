package com.qudump.jiangedan.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.injection.module.VideoListFragmentModule;
import com.qudump.jiangedan.model.LittleVideo;
import com.qudump.jiangedan.presenter.VideoListContract;
import com.qudump.jiangedan.presenter.VideoListPresenter;
import com.qudump.jiangedan.ui.adapter.VideoAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/9.
 */
public class VideoListFragment extends AbstractBaseFragment implements VideoListContract.View{

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    VideoListPresenter presenter;

    private VideoAdapter mAdapter;

    @Override
    void initDagger() {
        getApplication()
                .buildVideoComponent()
                .plus(new VideoListFragmentModule())
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_list,container,false);
        ButterKnife.bind(this,view);
        initView();
        presenter.setView(this);
        return view;
    }

    private void initView(){
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
        mAdapter = new VideoAdapter(getActivityContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
        presenter.setView(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void renderView(List<LittleVideo> videos) {
        mAdapter.setData(videos);
    }

    @Override
    public void stopRefresh() {
        if(mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPresenter(VideoListContract.Presenter presenter) {

    }

    @Override
    public void showErrMsg(String msg) {

    }
}
