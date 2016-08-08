package com.qudump.jiangedan.net.bean.mapper;

import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.net.bean.GirlPicBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dili on 2016/8/8.
 */
public class GirlPicBeanMapper {

    @Inject
    public GirlPicBeanMapper() {
    }

    public GirlPic transform(GirlPicBean girlPicBean) {
        GirlPic girlPic = null;
        if(null != girlPicBean) {
            girlPic = new GirlPic();
            girlPic.setId(girlPicBean.getComment_post_ID());
            girlPic.setCommentId(girlPicBean.getComment_ID());
            girlPic.setAuthorName(girlPicBean.getComment_author());
            girlPic.setAuthorEmail(girlPicBean.getComment_author_email());
            girlPic.setDate(girlPicBean.getComment_date());
            girlPic.setLikeCounts(girlPicBean.getVote_positive());
            girlPic.setDislikeCounts(girlPicBean.getVote_negative());
            girlPic.setPic(girlPicBean.getPics().get(0));
        }

        return girlPic;
    }

    public List<GirlPic> transform(List<GirlPicBean> girlPicBeanList) {
        List<GirlPic> girlPics = new ArrayList<>();
        if(null != girlPicBeanList) {
            for(GirlPicBean bean:girlPicBeanList) {
                GirlPic girlPic = transform(bean);
                if (null != girlPic) {
                    girlPics.add(girlPic);
                }
            }
        }

        return girlPics;
    }
}
