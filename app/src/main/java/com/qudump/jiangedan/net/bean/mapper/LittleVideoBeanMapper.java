package com.qudump.jiangedan.net.bean.mapper;

import com.qudump.jiangedan.model.LittleVideo;
import com.qudump.jiangedan.net.bean.LittleVideoBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dili on 2016/8/9.
 */
public class LittleVideoBeanMapper {

    @Inject
    public LittleVideoBeanMapper() {
    }

    public LittleVideo transform(LittleVideoBean bean){
        LittleVideo video = null;
        if(null != bean) {
            video = new LittleVideo();

            video.setId(bean.getComment_post_ID());
            video.setCommentId(bean.getComment_ID());
            if(bean.getVideos() != null && bean.getVideos().size() > 0) {
                video.setTitle(bean.getVideos().get(0).getTitle());
                video.setDescription(bean.getVideos().get(0).getDescription());
                video.setVideoLink(bean.getVideos().get(0).getPlayer());
                video.setWebPageLink(bean.getVideos().get(0).getLink());
                video.setThumbNail(bean.getVideos().get(0).getThumbnail());
                video.setTags(bean.getVideos().get(0).getTags());
            }

            video.setDate(bean.getComment_date());
            video.setAuthorName(bean.getComment_author());
            video.setAuthorEmail(bean.getComment_author_email());
            video.setLikeCounts(bean.getVote_positive());
            video.setDislikeCounts(bean.getVote_negative());
        }

        return video;
    }

    public List<LittleVideo> transform(List<LittleVideoBean> videoBeanList) {
        List<LittleVideo> videos = new ArrayList<>();
        for(LittleVideoBean bean:videoBeanList) {
            LittleVideo video = transform(bean);
            if(null != video) {
                videos.add(video);
            }
        }

        return videos;
    }
}
