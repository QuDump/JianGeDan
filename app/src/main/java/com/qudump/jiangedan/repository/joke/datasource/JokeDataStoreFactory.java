package com.qudump.jiangedan.repository.joke.datasource;

import com.qudump.jiangedan.cache.JokeCache;
import com.qudump.jiangedan.repository.BaseDataStoreFactory;
import com.qudump.jiangedan.repository.joke.datasource.cache.DiskJokeDataStore;
import com.qudump.jiangedan.repository.joke.datasource.net.CloudJokeDataStore;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by dili on 2016/8/8.
 */
public class JokeDataStoreFactory implements BaseDataStoreFactory<JokeDataStore> {
    private JokeCache jokeCache;
    @Inject
    Lazy<CloudJokeDataStore> lazyCloudJokeDataStore;
    @Inject
    Lazy<DiskJokeDataStore> lazyDiskJokeDataStore;

    @Inject
    public JokeDataStoreFactory(JokeCache jokeCache) {
        this.jokeCache = jokeCache;
    }

    @Override
    public JokeDataStore create(long id) {
        JokeDataStore jokeDataStore = null;
        if(!jokeCache.isExpired() && jokeCache.isCached(id)) {
            jokeDataStore = lazyDiskJokeDataStore.get();
        } else {
            jokeDataStore = create(id);
        }
        return jokeDataStore;
    }

    @Override
    public JokeDataStore createCloudDataStore() {
        return lazyCloudJokeDataStore.get();
    }
}
