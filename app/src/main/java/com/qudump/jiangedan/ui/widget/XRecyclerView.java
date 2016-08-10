package com.qudump.jiangedan.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

/**
 * Created by dili on 2016/8/10.
 */
public class XRecyclerView extends RecyclerView implements AbsListView.OnScrollListener {
    private XRecyclerViewListener mListener;
    private boolean mEnablePullRefresh = true;
    private boolean mPullRefreshing = false;

    private boolean mEnablePullLoad = true;
    private boolean mEnableAutoLoad = false;
    private boolean mPullLoading = false;

    public XRecyclerView(Context context) {
        super(context);
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View header) {
    }

    public void addFooterView(View footer) {

    }

    public void setPullRefreshEnable(boolean enable) {
        mEnablePullRefresh = enable;

        // disable, hide the content
    }

    public void setPullLoadEnable(boolean enable) {
        mEnablePullLoad = enable;
    }

    public void stopLoadMore(){
        if (mPullLoading) {
            mPullLoading = false;
            //mFooterView.setState(XFooterView.STATE_NORMAL);
        }
    }

    public void stopRefresh(){
        if (mPullRefreshing) {
            mPullRefreshing = false;
            //mFooterView.setState(XFooterView.STATE_NORMAL);
        }
    }

    private void refresh() {
        if (mEnablePullRefresh && null != mListener) {
            mListener.onRefresh();
        }
    }

    private void loadMore() {
        if (mEnablePullLoad && null != mListener) {
            mListener.onLoadMore();
        }
    }

    public void setXRecyclerViewListener(XRecyclerViewListener listener) {
        mListener = listener;
    }



    public interface XRecyclerViewListener {
        void onRefresh();
        void onLoadMore();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
