package com.qudump.jiangedan.repository.littlevideo.datasource.cache;

import com.qudump.jiangedan.cache.VideoCache;
import com.qudump.jiangedan.model.LittleVideo;
import com.qudump.jiangedan.repository.littlevideo.datasource.VideoDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/9.
 */
public class DiskVideoDataStore implements VideoDataStore {

    private VideoCache videoCache;

    @Inject
    public DiskVideoDataStore(VideoCache videoCache) {
        this.videoCache = videoCache;
    }

    @Override
    public Observable<List<LittleVideo>> videos(int page) {
        return null;
    }
}
