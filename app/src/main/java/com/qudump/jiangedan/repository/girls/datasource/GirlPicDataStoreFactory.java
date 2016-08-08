package com.qudump.jiangedan.repository.girls.datasource;

import com.qudump.jiangedan.cache.GirlPicCache;
import com.qudump.jiangedan.repository.BaseDataStoreFactory;
import com.qudump.jiangedan.repository.girls.datasource.cache.DiskGirlPicDataStore;
import com.qudump.jiangedan.repository.girls.datasource.net.CloudGirlPicDataStore;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by dili on 2016/8/8.
 */
public class GirlPicDataStoreFactory implements BaseDataStoreFactory<GirlPicDataStore> {

    private GirlPicCache girlPicCache;
    @Inject
    Lazy<CloudGirlPicDataStore> lazyCloudGirlPicDataStore;
    @Inject
    Lazy<DiskGirlPicDataStore> lazyDiskCloudGirlPicDataStore;

    @Inject
    public GirlPicDataStoreFactory(GirlPicCache girlPicCache) {
        this.girlPicCache = girlPicCache;
    }

    @Override
    public GirlPicDataStore create(long id) {
        GirlPicDataStore girlPicDataStore;
        if(!girlPicCache.isExpired() && girlPicCache.isCached(id)) {
            girlPicDataStore = lazyDiskCloudGirlPicDataStore.get();
        } else {
            girlPicDataStore = createCloudDataStore();
        }
        return girlPicDataStore;
    }

    @Override
    public GirlPicDataStore createCloudDataStore() {
        return lazyCloudGirlPicDataStore.get();
    }
}
