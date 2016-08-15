package com.qudump.jiangedan.repository.littlevideo;

import com.qudump.jiangedan.model.LittleVideo;
import com.qudump.jiangedan.net.bean.CommentNumberRespBean;
import com.qudump.jiangedan.repository.littlevideo.datasource.VideoDataStore;
import com.qudump.jiangedan.repository.littlevideo.datasource.VideoDataStoreFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/9.
 */
public class VideoRepository {

    private VideoDataStoreFactory videoDataStoreFactory;
    private List<LittleVideo> mVideos;

    @Inject
    public VideoRepository(VideoDataStoreFactory videoDataStoreFactory) {
        this.videoDataStoreFactory = videoDataStoreFactory;
    }

    public Observable<List<LittleVideo>> videos(int page) {
        VideoDataStore videoDataStore = videoDataStoreFactory.createCloudDataStore();
        return videoDataStore
                .videos(page)
                .doOnNext(videos->mVideos = videos)
                .flatMap(videos->queryCommentCounts(videoDataStore,getCommentParams(videos)))
                .flatMap(commentNumberRespBeen->Observable.just(appendCommentCountToPicModel(mVideos,commentNumberRespBeen)));
    }

    private String getCommentParams(List<LittleVideo> videos) {
        StringBuilder params = new StringBuilder();
        for(LittleVideo video:videos) {
            String item = "comment-"+video.getCommentId()+",";
            params.append(item);
        }

        return params.toString();
    }

    private Observable<List<CommentNumberRespBean>> queryCommentCounts(VideoDataStore dataStore, String params) {
        return dataStore.commentNumbers(params);
    }

    private List<LittleVideo> appendCommentCountToPicModel(List<LittleVideo> videos, List<CommentNumberRespBean> commentBeans) {
        for(LittleVideo video:videos) {
            for(CommentNumberRespBean bean:commentBeans){
                if(video.getCommentId() == bean.getCommentId()) {
                    video.setComments(bean.getComments());
                    continue;
                }
            }
        }
        return videos;
    }
}
