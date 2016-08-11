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
import com.qudump.jiangedan.injection.module.GirlPicListFragmentModule;
import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.presenter.GirlPicContract;
import com.qudump.jiangedan.presenter.GirlPicPresenter;
import com.qudump.jiangedan.ui.adapter.PicAdapter;
import com.qudump.jiangedan.ui.widget.LoadMoreFooterView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/8.
 */
public class GirlPicListFragment extends AbstractBaseFragment implements GirlPicContract.View{

    @Bind(R.id.rv_content)
    IRecyclerView recyclerView;

    @Inject
    GirlPicPresenter presenter;

    private PicAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

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
        presenter.setView(this);

        mAdapter = new PicAdapter(getActivityContext());
        mLayoutManager = new LinearLayoutManager(getActivityContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
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

    }

    @Override
    public void renderView(List<GirlPic> girlPics) {
        mAdapter.setData(girlPics);
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
