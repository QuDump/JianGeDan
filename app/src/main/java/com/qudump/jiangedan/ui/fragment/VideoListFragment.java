package com.qudump.jiangedan.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.qudump.jiangedan.R;
import com.qudump.jiangedan.injection.module.VideoListFragmentModule;
import com.qudump.jiangedan.model.LittleVideo;
import com.qudump.jiangedan.presenter.VideoListContract;
import com.qudump.jiangedan.presenter.VideoListPresenter;
import com.qudump.jiangedan.ui.adapter.VideoAdapter;
import com.qudump.jiangedan.ui.widget.LoadMoreFooterView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/9.
 */
public class VideoListFragment extends AbstractBaseFragment implements VideoListContract.View{

    @Bind(R.id.recycler_view)
    IRecyclerView mRecyclerView;
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

        mAdapter = new VideoAdapter(getActivityContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setIAdapter(mAdapter);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadRecent();
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(View view) {
                LoadMoreFooterView loadMoreFooterView = (LoadMoreFooterView)mRecyclerView.getLoadMoreFooterView();
                if (loadMoreFooterView.canLoadMore() && mAdapter.getItemCount() > 0) {
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
                    presenter.loadNextPage();
                }
            }
        });
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
        mRecyclerView.setRefreshing(false);
        LoadMoreFooterView loadMoreFooterView = (LoadMoreFooterView)mRecyclerView.getLoadMoreFooterView();
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
    }

    @Override
    public void showErrMsg(String msg) {

    }
}
