package com.qudump.jiangedan.repository.littlevideo.datasource.net;

import com.qudump.jiangedan.cache.VideoCache;
import com.qudump.jiangedan.model.LittleVideo;
import com.qudump.jiangedan.net.bean.mapper.LittleVideoBeanMapper;
import com.qudump.jiangedan.net.service.littlevideo.LittleVideoApiService;
import com.qudump.jiangedan.repository.littlevideo.datasource.VideoDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dili on 2016/8/9.
 */
public class CloudVideoDataStore implements VideoDataStore {

    private LittleVideoApiService apiService;
    private LittleVideoBeanMapper mapper;
    private VideoCache videoCache;
    private Action1<LittleVideo> saveToCacheAction = littleVideo -> {
        if(null != littleVideo) {
            videoCache.put(littleVideo);
        }
    };

    @Inject
    public CloudVideoDataStore(LittleVideoApiService apiService, LittleVideoBeanMapper mapper, VideoCache videoCache) {
        this.apiService = apiService;
        this.mapper = mapper;
        this.videoCache = videoCache;
    }

    @Override
    public Observable<List<LittleVideo>> videos(int page) {
        return apiService.videoList(page).map(mapper::transform);
    }
}
