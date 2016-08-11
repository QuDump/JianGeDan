package com.qudump.jiangedan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.aspsine.irecyclerview.IViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qudump.jiangedan.R;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.ui.PostDetailActivity;

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

        String imgUrl = post.getThumbImg().get(0);
        if(post.getThumbImg().get(0).contains("custom")) {
            imgUrl = post.getThumbImg().get(0).replace("custom", "medium");
        }

        holder.img.setImageURI(Uri.parse(imgUrl));

        setAnimation(holder.card, position);
        holder.card.setOnClickListener(listener->{
            Intent intent = new Intent(mContext, PostDetailActivity.class);
            intent.putExtra(PostDetailActivity.EXT_POST_KEY,mPosts.get(position));
            mContext.startActivity(intent);
        });

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

    public class ViewHolder extends IViewHolder {
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_info)
        TextView tv_info;
        @Bind(R.id.tv_share)
        TextView tv_share;
        @Bind(R.id.img)
        SimpleDraweeView img;
        @Bind(R.id.card)
        CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
