package com.qudump.jiangedan.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qudump.jiangedan.R;
import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.utils.String2TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/8.
 */
public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {

    private Context mContext;
    private List<GirlPic> picList = new ArrayList<>();
    private int lastPosition = -1;

    public PicAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<GirlPic> pics) {
        picList = pics;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_pic_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        final GirlPic item = picList.get(pos);

        if(item.getPics().get(0).toLowerCase().endsWith("gif")){
            DraweeController draweeController = Fresco
                    .newDraweeControllerBuilder()
                    .setUri(item.getPics().get(0))
                    .setAutoPlayAnimations(true)
                    .build();
            viewHolder.img.setController(draweeController);
        } else {
            viewHolder.img.setImageURI(Uri.parse(item.getPics().get(0)));
        }

        //viewHolder.img.setImageURI(Uri.parse(item.getPic()));


        viewHolder.tvAuthor.setText(item.getAuthorName());
        viewHolder.tvDate.setText(String2TimeUtil.dateString2GoodExperienceFormat(item.getDate()));

        setAnimation(viewHolder.card,pos);
    }

    @Override
    public int getItemCount() {
        return picList.size();
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
        @Bind(R.id.img)
        SimpleDraweeView img;
        @Bind(R.id.tv_info)
        TextView tvAuthor;
        @Bind(R.id.tv_date)
        TextView tvDate;
        @Bind(R.id.card)
        CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
