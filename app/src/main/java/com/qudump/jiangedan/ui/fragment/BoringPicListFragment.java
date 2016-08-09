package com.qudump.jiangedan.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.injection.module.BoringPicListFragmentModule;
import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.presenter.BoringPicContract;
import com.qudump.jiangedan.presenter.BoringPicListPresenter;
import com.qudump.jiangedan.ui.adapter.BoringPicAdapter;

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
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadRecent();
            }
        });
        mAdapter = new BoringPicAdapter(getActivityContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mAdapter);
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
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPresenter(BoringPicContract.Presenter presenter) {

    }

    @Override
    public void showErrMsg(String msg) {

    }
}
