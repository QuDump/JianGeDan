package com.qudump.jiangedan.repository.littlevideo.datasource;

import com.qudump.jiangedan.cache.VideoCache;
import com.qudump.jiangedan.repository.BaseDataStoreFactory;
import com.qudump.jiangedan.repository.littlevideo.datasource.cache.DiskVideoDataStore;
import com.qudump.jiangedan.repository.littlevideo.datasource.net.CloudVideoDataStore;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by dili on 2016/8/9.
 */
public class VideoDataStoreFactory implements BaseDataStoreFactory<VideoDataStore> {
    private VideoCache videoCache;
    @Inject
    Lazy<CloudVideoDataStore> cloudVideoDataStoreLazy;
    @Inject
    Lazy<DiskVideoDataStore> diskVideoDataStoreLazy;

    @Inject
    public VideoDataStoreFactory(VideoCache videoCache) {
        this.videoCache = videoCache;
    }

    @Override
    public VideoDataStore create(long id) {
        VideoDataStore videoDataStore;
        if(!videoCache.isExpired() && videoCache.isCached(id)) {
            videoDataStore = diskVideoDataStoreLazy.get();
        } else {
            videoDataStore = createCloudDataStore();
        }
        return videoDataStore;
    }

    @Override
    public VideoDataStore createCloudDataStore() {
        return cloudVideoDataStoreLazy.get();
    }
}
