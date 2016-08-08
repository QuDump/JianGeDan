package com.qudump.jiangedan.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.injection.module.JokeListFragmentModule;
import com.qudump.jiangedan.model.Joke;
import com.qudump.jiangedan.presenter.JokeListContract;
import com.qudump.jiangedan.presenter.JokeListPresenter;
import com.qudump.jiangedan.ui.adapter.JokeAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by qidong on 2016/8/6.
 */
public class JokeListFragment extends AbstractBaseFragment implements JokeListContract.View {

    @Bind(R.id.rv_content)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

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
        mAdapter = new JokeAdapter(getActivityContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mAdapter);
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
        if(swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPresenter(JokeListContract.Presenter presenter) {

    }

    @Override
    public void showErrMsg(String msg) {

    }
}
