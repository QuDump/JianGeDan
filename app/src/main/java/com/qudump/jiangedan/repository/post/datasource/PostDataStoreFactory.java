package com.qudump.jiangedan.repository.post.datasource;

import com.qudump.jiangedan.cache.PostCache;
import com.qudump.jiangedan.net.bean.mapper.PostBeanDataMapper;
import com.qudump.jiangedan.net.service.post.PostApiService;
import com.qudump.jiangedan.repository.post.datasource.cache.DiskPostDataStore;
import com.qudump.jiangedan.repository.post.datasource.net.CloudPostDataStore;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by dili on 2016/8/4.
 */
public class PostDataStoreFactory {
    private PostCache postCache;
    private PostApiService postApiService;
    private PostBeanDataMapper postBeanDataMapper;

    @Inject
    Lazy<CloudPostDataStore> lazyCloudPostDataStore;
    @Inject
    Lazy<DiskPostDataStore> lazyDiskPostDataStore;

    @Inject
    public PostDataStoreFactory(PostCache postCache) {
        this.postCache = postCache;
    }

    public PostDataStore create(long postId) {
        PostDataStore postDataStore;

        if(!postCache.isExpired() && postCache.isCached(postId)){
            postDataStore = lazyDiskPostDataStore.get();
        } else {
            postDataStore = createCloudDataStore();
        }

        return postDataStore;
    }

    public PostDataStore createCloudDataStore(){
        return lazyCloudPostDataStore.get();
    }
}
