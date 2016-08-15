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
import com.qudump.jiangedan.model.LittleVideo;
import com.qudump.jiangedan.ui.CommentListActivity;
import com.qudump.jiangedan.utils.String2TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/9.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    private Context mContext;
    private List<LittleVideo> videoList = new ArrayList<>();
    private int lastPosition = -1;

    public VideoAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<LittleVideo> pics) {
        videoList = pics;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_video_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        final LittleVideo item = videoList.get(pos);

        viewHolder.img.setImageURI(Uri.parse(item.getThumbNail()));
        viewHolder.tvTitle.setText(item.getTitle());
        viewHolder.tvAuthor.setText(item.getAuthorName());
        viewHolder.tvDate.setText(String2TimeUtil.dateString2GoodExperienceFormat(item.getDate()));
        viewHolder.tvLike.setText(String.valueOf(item.getLikeCounts()));
        viewHolder.tvDislike.setText(String.valueOf(item.getDislikeCounts()));
        viewHolder.tvComments.setText(String.valueOf(item.getComments()));
        viewHolder.tvComments.setOnClickListener(listener->{
            Intent intent = new Intent(mContext, CommentListActivity.class);
            intent.putExtra(CommentListActivity.EXT_ID_KEY, item.getCommentId());
            intent.putExtra(CommentListActivity.EXT_IS_POST_KEY,false);
            mContext.startActivity(intent);
        });

        setAnimation(viewHolder.card,pos);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
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


    public class ViewHolder extends IViewHolder {
        @Bind(R.id.img)
        SimpleDraweeView img;
        @Bind(R.id.tv_author)
        TextView tvAuthor;
        @Bind(R.id.tv_time)
        TextView tvDate;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.card)
        CardView card;
        @Bind(R.id.tv_comments)
        TextView tvComments;
        @Bind(R.id.tv_like_count)
        TextView tvLike;
        @Bind(R.id.tv_dislike_count)
        TextView tvDislike;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
