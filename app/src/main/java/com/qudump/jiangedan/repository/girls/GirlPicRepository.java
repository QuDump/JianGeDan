package com.qudump.jiangedan.repository.girls;

import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.repository.girls.datasource.GirlPicDataStore;
import com.qudump.jiangedan.repository.girls.datasource.GirlPicDataStoreFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public class GirlPicRepository {
    GirlPicDataStoreFactory girlPicDataStoreFactory;

    @Inject
    public GirlPicRepository(GirlPicDataStoreFactory girlPicDataStoreFactory) {
        this.girlPicDataStoreFactory = girlPicDataStoreFactory;
    }

    public Observable<List<GirlPic>> girlPics(int page) {
        GirlPicDataStore girlPicDataStore = girlPicDataStoreFactory.createCloudDataStore();
        return girlPicDataStore.girlPics(page);
    }
}
