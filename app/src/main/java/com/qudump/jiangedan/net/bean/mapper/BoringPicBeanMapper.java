package com.qudump.jiangedan.net.bean.mapper;

import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.net.bean.BoringPicBean;
import com.qudump.jiangedan.net.bean.GirlPicBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicBeanMapper {

    @Inject
    public BoringPicBeanMapper() {
    }

    public BoringPic transform(BoringPicBean boringPicBean) {
        BoringPic boringPic = null;
        if(null != boringPicBean) {
            boringPic = new BoringPic();
            boringPic.setId(boringPicBean.getComment_post_ID());
            boringPic.setCommentId(boringPicBean.getComment_ID());
            boringPic.setAuthorName(boringPicBean.getComment_author());
            boringPic.setAuthorEmail(boringPicBean.getComment_author_email());
            boringPic.setDate(boringPicBean.getComment_date());
            boringPic.setLikeCounts(boringPicBean.getVote_positive());
            boringPic.setDislikeCounts(boringPicBean.getVote_negative());
            boringPic.setPic(boringPicBean.getPics().get(0));
        }

        return boringPic;
    }

    public List<BoringPic> transform(List<BoringPicBean> boringPicList) {
        List<BoringPic> boringPics = new ArrayList<>();
        if(null != boringPicList) {
            for(BoringPicBean bean:boringPicList) {
                BoringPic boringPic = transform(bean);
                if (null != boringPic) {
                    boringPics.add(boringPic);
                }
            }
        }

        return boringPics;
    }
}
