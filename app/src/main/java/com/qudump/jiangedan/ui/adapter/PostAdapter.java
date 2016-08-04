package com.qudump.jiangedan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.model.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/3.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context mContext;
    private List<Post> mPosts = new ArrayList<>();
    private int lastPosition = -1;

    public PostAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<Post> posts){
        mPosts = posts;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_post_list,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Post post = mPosts.get(position);
        holder.tv_title.setText(post.getTitle());
        holder.tv_info.setText(post.getAuthor().getNickname());
        //TODO set img and share

        setAnimation(holder.card, position);

    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R
                    .anim.item_bottom_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.card.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_info)
        TextView tv_info;
        @Bind(R.id.tv_share)
        TextView tv_share;
        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.card)
        CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(listener->{
                int pos = getAdapterPosition();
                //TODO goto detain actvity
            });
        }
    }
}
