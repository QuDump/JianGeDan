package com.qudump.jiangedan.repository.boringpic;

import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.repository.boringpic.datasource.BoringPicDataStore;
import com.qudump.jiangedan.repository.boringpic.datasource.BoringPicDataStoreFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicRepository {
    private BoringPicDataStoreFactory boringPicDataStoreFactory;

    @Inject
    public BoringPicRepository(BoringPicDataStoreFactory boringPicDataStoreFactory) {
        this.boringPicDataStoreFactory = boringPicDataStoreFactory;
    }

    public Observable<List<BoringPic>> boringPics(int page) {
        BoringPicDataStore boringPicDataStore = boringPicDataStoreFactory.createCloudDataStore();
        return boringPicDataStore.pics(page);
    }
}
