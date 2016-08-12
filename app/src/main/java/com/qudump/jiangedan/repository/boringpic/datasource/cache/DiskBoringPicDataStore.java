package com.qudump.jiangedan.repository.boringpic.datasource.cache;

import com.qudump.jiangedan.cache.BoringPicCache;
import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.net.bean.CommentNumberRespBean;
import com.qudump.jiangedan.repository.boringpic.datasource.BoringPicDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by qidong on 2016/8/8.
 */
public class DiskBoringPicDataStore implements BoringPicDataStore {

    private BoringPicCache boringPicCache;

    @Inject
    public DiskBoringPicDataStore(BoringPicCache boringPicCache) {
        this.boringPicCache = boringPicCache;
    }

    @Override
    public Observable<List<BoringPic>> pics(int page) {
        return null;
    }

    @Override
    public Observable<List<CommentNumberRespBean>> comments(String params) {
        return null;
    }
}
