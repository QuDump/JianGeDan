package com.qudump.jiangedan.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.qudump.jiangedan.R;
import com.qudump.jiangedan.injection.module.PostListFragmentModule;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.presenter.PostListContract;
import com.qudump.jiangedan.presenter.PostListPresenter;
import com.qudump.jiangedan.ui.adapter.PostAdapter;
import com.qudump.jiangedan.ui.widget.LoadMoreFooterView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/3.
 */
public class PostListFragment extends AbstractBaseFragment implements PostListContract.View{

    @Bind(R.id.recycler_view)
    IRecyclerView mRecyclerView;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext()));
        mAdapter = new PostAdapter(getActivityContext());
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
    }

    @Override
    public void renderView(List<Post> posts) {
        mAdapter.setData(posts);
    }

    @Override
    public void showErrMsg(String msg) {
        Toast.makeText(getActivityContext(),msg,Toast.LENGTH_SHORT);
        LoadMoreFooterView loadMoreFooterView = (LoadMoreFooterView)mRecyclerView.getLoadMoreFooterView();
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.ERROR);
    }

    @Override
    public void stopRefresh() {
        mRecyclerView.setRefreshing(false);
        LoadMoreFooterView loadMoreFooterView = (LoadMoreFooterView)mRecyclerView.getLoadMoreFooterView();
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
    }

}
