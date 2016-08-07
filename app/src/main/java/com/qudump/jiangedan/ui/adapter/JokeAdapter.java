package com.qudump.jiangedan.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.model.Joke;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by qidong on 2016/8/6.
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder>{

    private Context mContext;
    private List<Joke> mJokes = new ArrayList<>();

    public JokeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Joke> jokes){
        mJokes = jokes;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_joke,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mJokes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_info)
        TextView tvAuther;
        @Bind(R.id.tv_time)
        TextView tvPostTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
