package com.qudump.jiangedan.repository.boringpic.datasource;

import com.qudump.jiangedan.cache.BoringPicCache;
import com.qudump.jiangedan.repository.BaseDataStoreFactory;
import com.qudump.jiangedan.repository.boringpic.datasource.cache.DiskBoringPicDataStore;
import com.qudump.jiangedan.repository.boringpic.datasource.net.CloudBoringPicDataStore;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicDataStoreFactory implements BaseDataStoreFactory<BoringPicDataStore>{
    private BoringPicCache boringPicCache;
    @Inject
    Lazy<CloudBoringPicDataStore> lazyCloudBoringPicDataStore;

    @Inject
    Lazy<DiskBoringPicDataStore> lazyDiskBoringPicDataStore;

    @Inject
    public BoringPicDataStoreFactory(BoringPicCache boringPicCache) {
        this.boringPicCache = boringPicCache;
    }

    @Override
    public BoringPicDataStore create(long id) {
        BoringPicDataStore picDataStore;
        if(!boringPicCache.isExpired() && boringPicCache.isCached(id)) {
            picDataStore = lazyDiskBoringPicDataStore.get();
        } else {
            picDataStore = createCloudDataStore();
        }
        return picDataStore;
    }

    @Override
    public BoringPicDataStore createCloudDataStore() {
        return lazyCloudBoringPicDataStore.get();
    }
}
