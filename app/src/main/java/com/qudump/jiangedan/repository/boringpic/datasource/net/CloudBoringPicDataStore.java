package com.qudump.jiangedan.repository.boringpic.datasource.net;

import com.qudump.jiangedan.cache.BoringPicCache;
import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.net.bean.CommentNumberRespBean;
import com.qudump.jiangedan.net.bean.mapper.BoringPicBeanMapper;
import com.qudump.jiangedan.net.service.boringpic.BoringPicApiService;
import com.qudump.jiangedan.net.service.comment.CommentApiService;
import com.qudump.jiangedan.repository.boringpic.datasource.BoringPicDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by qidong on 2016/8/8.
 */
public class CloudBoringPicDataStore implements BoringPicDataStore {

    private BoringPicApiService apiService;
    private CommentApiService commentApiService;
    private BoringPicCache boringPicCache;
    private BoringPicBeanMapper mapper;
    private Action1<BoringPic> saveToCacheAction = boringPic -> {
        if (null != boringPic) {
            boringPicCache.put(boringPic);
        }
    };

    @Inject
    public CloudBoringPicDataStore(BoringPicApiService apiService, CommentApiService commentApiService, BoringPicCache boringPicCache, BoringPicBeanMapper mapper) {
        this.apiService = apiService;
        this.commentApiService = commentApiService;
        this.boringPicCache = boringPicCache;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<BoringPic>> pics(int page) {

        return apiService.boringPics(page).map(mapper::transform);
    }

    @Override
    public Observable<List<CommentNumberRespBean>> commentNumbers(String params) {
        return commentApiService.commentNumbers(params);
    }
}
