package com.qudump.jiangedan.repository.littlevideo;

import com.qudump.jiangedan.model.LittleVideo;
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

    @Inject
    public VideoRepository(VideoDataStoreFactory videoDataStoreFactory) {
        this.videoDataStoreFactory = videoDataStoreFactory;
    }

    public Observable<List<LittleVideo>> videos(int page) {
        VideoDataStore videoDataStore = videoDataStoreFactory.createCloudDataStore();
        return videoDataStore.videos(page);
    }
}
