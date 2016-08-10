package com.qudump.jiangedan.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
//    @Bind(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.container)
    RelativeLayout mContainer;

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
//        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                presenter.loadRecent();
//            }
//        });
        mAdapter = new PicAdapter(getActivityContext());
        mLayoutManager = new LinearLayoutManager(getActivityContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        presenter.setView(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //得到当前显示的最后一个item的view
                View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount()-1);
                //得到lastChildView的bottom坐标值
                int lastChildBottom = lastChildView.getBottom();
                //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
                int recyclerBottom =  recyclerView.getBottom()-recyclerView.getPaddingBottom();
                //通过这个lastChildView得到这个view当前的position值
                int lastPosition  = recyclerView.getLayoutManager().getPosition(lastChildView);

                //判断lastChildView的bottom值跟recyclerBottom
                //判断lastPosition是不是最后一个position
                //如果两个条件都满足则说明是真正的滑动到了底部
                if(lastChildBottom == recyclerBottom && lastPosition == recyclerView.getLayoutManager().getItemCount()-1 ){
                    Toast.makeText(getActivityContext(), "滑动到底了", Toast.LENGTH_SHORT).show();
                    TextView textView = new TextView(getActivityContext());
                    textView.setText("加载中....");
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ViewGroup parentView = (ViewGroup) recyclerView.getParent();
                    parentView.addView(textView,layoutParams);
                }
//                int visibleItemCount = recyclerView.getChildCount();
//                int totalItemCount = mLayoutManager.getItemCount();
//
//                int firstVisibleItemIndex = mLayoutManager.findFirstVisibleItemPosition();
//                int lastVisibleItemIndex = mLayoutManager.findLastVisibleItemPosition();
//                if(lastVisibleItemIndex == (totalItemCount-1)) {
//                    TextView textView = new TextView(getActivityContext());
//                    textView.setText("加载中....");
//                    swipeRefreshLayout.addView(textView);
//                }
//
//                boolean loading = true;
//                int previousTotal = -1;
//
//                //synchronizew loading state when item count changes
//                if (loading) {
//                    if (totalItemCount > previousTotal) {
//                        loading = false;
//                        previousTotal = totalItemCount;
//                    }
//                }
//                if (!loading)
//                    if ((totalItemCount - visibleItemCount) <= firstVisibleItemIndex) {
//                        // Loading NOT in progress and end of list has been reached
//                        // also triggered if not enough items to fill the screen
//                        // if you start loading
//                        loading = true;
//                    } else if (firstVisibleItemIndex == 0){
//                        // top of list reached
//                        // if you start loading
//                        loading = true;
//                    }
            }
        });
    }

    @Override
    public void renderView(List<GirlPic> girlPics) {
        mAdapter.setData(girlPics);
    }

    @Override
    public void stopRefresh() {
//        if(swipeRefreshLayout.isRefreshing()){
//            swipeRefreshLayout.setRefreshing(false);
//        }
    }

    @Override
    public void setPresenter(GirlPicContract.Presenter presenter) {

    }

    @Override
    public void showErrMsg(String msg) {

    }
}
