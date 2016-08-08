package com.qudump.jiangedan.repository.girls.datasource.cache;

import com.qudump.jiangedan.cache.GirlPicCache;
import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.repository.girls.datasource.GirlPicDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public class DiskGirlPicDataStore implements GirlPicDataStore {

    private GirlPicCache girlPicCache;

    @Inject
    public DiskGirlPicDataStore(GirlPicCache girlPicCache) {
        this.girlPicCache = girlPicCache;
    }

    @Override
    public Observable<List<GirlPic>> girlPics(int page) {
        return null;
    }
}
