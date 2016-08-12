package com.qudump.jiangedan.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.IViewHolder;
import com.qudump.jiangedan.R;
import com.qudump.jiangedan.model.Comment;
import com.qudump.jiangedan.utils.String2TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/11.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context context;
    private List<Comment> comments = new ArrayList<>();

    public CommentAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Comment> commentList) {
        comments = commentList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_comment_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Comment item = comments.get(position);

        holder.tvName.setText(item.getAuthorName());
        holder.tvTime.setText(String2TimeUtil.dateString2GoodExperienceFormat(item.getDate()));
        holder.tvContent.setText(item.getContent());
        holder.tvLike.setText(String.valueOf(item.getLike()));
        holder.tvDislike.setText(String.valueOf(item.getDislike()));
        holder.rlLike.setOnClickListener(listener->{
            //TODO post like count
        });
        holder.rlDislike.setOnClickListener(listener->{
            //TODO post dislike count
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends IViewHolder {
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_dislike_count)
        TextView tvDislike;
        @Bind(R.id.tv_like_count)
        TextView tvLike;
        @Bind(R.id.rl_like)
        RelativeLayout rlLike;
        @Bind(R.id.rl_dislike)
        RelativeLayout rlDislike;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
