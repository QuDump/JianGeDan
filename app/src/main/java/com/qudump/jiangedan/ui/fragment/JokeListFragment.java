package com.qudump.jiangedan.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.qudump.jiangedan.R;
import com.qudump.jiangedan.injection.module.JokeListFragmentModule;
import com.qudump.jiangedan.model.Joke;
import com.qudump.jiangedan.presenter.JokeListContract;
import com.qudump.jiangedan.presenter.JokeListPresenter;
import com.qudump.jiangedan.ui.adapter.JokeAdapter;
import com.qudump.jiangedan.ui.widget.LoadMoreFooterView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by qidong on 2016/8/6.
 */
public class JokeListFragment extends AbstractBaseFragment implements JokeListContract.View {

    @Bind(R.id.rv_content)
    IRecyclerView recyclerView;

    @Inject
    JokeListPresenter presenter;

    private JokeAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke_list,container,false);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {

        mAdapter = new JokeAdapter(getActivityContext());
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
    void initDagger() {
        getApplication()
                .buildJokeComponent()
                .plus(new JokeListFragmentModule())
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            presenter.start();
        } else {
            presenter.destroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void renderView(List<Joke> jokes) {
        mAdapter.setData(jokes);
    }

    @Override
    public void stopRefresh() {
        recyclerView.setRefreshing(false);
        LoadMoreFooterView loadMoreFooterView = (LoadMoreFooterView)recyclerView.getLoadMoreFooterView();
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
    }

    @Override
    public void setPresenter(JokeListContract.Presenter presenter) {

    }

    @Override
    public void showErrMsg(String msg) {

    }
}
