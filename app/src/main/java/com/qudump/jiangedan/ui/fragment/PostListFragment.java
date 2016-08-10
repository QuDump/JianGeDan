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
//    @Bind(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout mSwipeRefreshLayout;

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
//        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                presenter.loadRecent();
//            }
//        });
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
                presenter.loadNextPage();
            }
        });
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                //得到当前显示的最后一个item的view
//                View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount()-1);
//                //得到lastChildView的bottom坐标值
//                int lastChildBottom = lastChildView.getBottom();
//                //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
//                int recyclerBottom =  recyclerView.getBottom()-recyclerView.getPaddingBottom();
//                //通过这个lastChildView得到这个view当前的position值
//                int lastPosition  = recyclerView.getLayoutManager().getPosition(lastChildView);
//
//                //判断lastChildView的bottom值跟recyclerBottom
//                //判断lastPosition是不是最后一个position
//                //如果两个条件都满足则说明是真正的滑动到了底部
//                if(lastChildBottom == recyclerBottom && lastPosition == recyclerView.getLayoutManager().getItemCount()-1 ){
//                    Toast.makeText(getActivityContext(), "滑动到底了", Toast.LENGTH_SHORT).show();
//                    TextView textView = new TextView(getActivityContext());
//                    textView.setText("加载中....");
//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    ViewGroup parentView = (ViewGroup) recyclerView.getParent();
//                    parentView.addView(textView,layoutParams);
//                }
////                int visibleItemCount = recyclerView.getChildCount();
////                int totalItemCount = mLayoutManager.getItemCount();
////
////                int firstVisibleItemIndex = mLayoutManager.findFirstVisibleItemPosition();
////                int lastVisibleItemIndex = mLayoutManager.findLastVisibleItemPosition();
////                if(lastVisibleItemIndex == (totalItemCount-1)) {
////                    TextView textView = new TextView(getActivityContext());
////                    textView.setText("加载中....");
////                    swipeRefreshLayout.addView(textView);
////                }
////
////                boolean loading = true;
////                int previousTotal = -1;
////
////                //synchronizew loading state when item count changes
////                if (loading) {
////                    if (totalItemCount > previousTotal) {
////                        loading = false;
////                        previousTotal = totalItemCount;
////                    }
////                }
////                if (!loading)
////                    if ((totalItemCount - visibleItemCount) <= firstVisibleItemIndex) {
////                        // Loading NOT in progress and end of list has been reached
////                        // also triggered if not enough items to fill the screen
////                        // if you start loading
////                        loading = true;
////                    } else if (firstVisibleItemIndex == 0){
////                        // top of list reached
////                        // if you start loading
////                        loading = true;
////                    }
//            }
//        });

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
//        if (mSwipeRefreshLayout.isRefreshing()) {
//            mSwipeRefreshLayout.setRefreshing(false);
//        }
    }

    @Override
    public void setPresenter(PostListContract.Presenter presenter) {

    }
}
