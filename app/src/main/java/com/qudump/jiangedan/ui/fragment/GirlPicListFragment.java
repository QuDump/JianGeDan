package com.qudump.jiangedan.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.injection.module.GirlPicListFragmentModule;
import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.presenter.GirlPicContract;
import com.qudump.jiangedan.presenter.GirlPicPresenter;
import com.qudump.jiangedan.ui.adapter.PicAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/8.
 */
public class GirlPicListFragment extends AbstractBaseFragment implements GirlPicContract.View{

    @Bind(R.id.rv_content)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    GirlPicPresenter presenter;

    private PicAdapter mAdapter;

    @Override
    void initDagger() {
        getApplication()
                .buildGirlPicComponent()
                .plus(new GirlPicListFragmentModule())
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl_pic_list,container,false);
        ButterKnife.bind(this,view);
        init();
        return view;
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
        mAdapter = new PicAdapter(getActivityContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mAdapter);
        presenter.setView(this);
    }

    @Override
    public void renderView(List<GirlPic> girlPics) {
        mAdapter.setData(girlPics);
    }

    @Override
    public void stopRefresh() {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPresenter(GirlPicContract.Presenter presenter) {

    }

    @Override
    public void showErrMsg(String msg) {

    }
}
