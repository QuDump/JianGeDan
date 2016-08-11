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
import com.qudump.jiangedan.injection.module.BoringPicListFragmentModule;
import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.presenter.BoringPicContract;
import com.qudump.jiangedan.presenter.BoringPicListPresenter;
import com.qudump.jiangedan.ui.adapter.BoringPicAdapter;
import com.qudump.jiangedan.ui.widget.LoadMoreFooterView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicListFragment extends AbstractBaseFragment implements BoringPicContract.View{
    @Inject
    BoringPicListPresenter presenter;

    @Bind(R.id.rv_content)
    IRecyclerView recyclerView;

    private BoringPicAdapter mAdapter;


    @Override
    void initDagger() {
        getApplication()
                .buildBoringPicComponent()
                .plus(new BoringPicListFragmentModule())
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boring_pic_list,container,false);
        ButterKnife.bind(this,view);
        init();
        presenter.setView(this);
        return view;
    }

    private void init(){

        mAdapter = new BoringPicAdapter(getActivityContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setIAdapter(mAdapter);
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadRecent();
            }
        });
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(View view) {
                LoadMoreFooterView loadMoreFooterView = (LoadMoreFooterView)recyclerView.getLoadMoreFooterView();
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
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destroy();
    }

    @Override
    public void renderView(List<BoringPic> boringPicList) {
        mAdapter.setData(boringPicList);
    }

    @Override
    public void stopRefresh() {
        recyclerView.setRefreshing(false);
        LoadMoreFooterView loadMoreFooterView = (LoadMoreFooterView)recyclerView.getLoadMoreFooterView();
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
    }

    @Override
    public void showErrMsg(String msg) {

    }
}
