package com.qudump.jiangedan.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.model.Joke;
import com.qudump.jiangedan.utils.String2TimeUtil;

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
    private int lastPosition = -1;

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
        final Joke joke = mJokes.get(position);
        holder.tvAuther.setText(joke.getAuthorName());
        holder.tvContent.setText(joke.getContent());
        holder.tvPostTime.setText(String2TimeUtil.dateString2GoodExperienceFormat(joke.getDate()));

        setAnimation(holder.card, position);

    }

    @Override
    public int getItemCount() {
        return mJokes.size();
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.card.clearAnimation();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R
                    .anim.item_bottom_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_info)
        TextView tvAuther;
        @Bind(R.id.tv_time)
        TextView tvPostTime;
        @Bind(R.id.card)
        CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
