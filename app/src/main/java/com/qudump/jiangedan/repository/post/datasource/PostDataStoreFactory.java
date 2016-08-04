package com.qudump.jiangedan.repository.post.datasource;

import com.qudump.jiangedan.cache.PostCache;
import com.qudump.jiangedan.net.service.post.PostApiService;
import com.qudump.jiangedan.repository.post.datasource.cache.DiskPostDataStore;
import com.qudump.jiangedan.repository.post.datasource.net.CloudPostDataStore;

import javax.inject.Inject;

/**
 * Created by dili on 2016/8/4.
 */
public class PostDataStoreFactory {
    private PostCache postCache;
    private PostApiService postApiService;

    @Inject
    public PostDataStoreFactory(PostCache postCache, PostApiService postApiService) {
        this.postCache = postCache;
        this.postApiService = postApiService;
    }

    public PostDataStore create(long postId) {
        PostDataStore postDataStore;

        if(!postCache.isExpired() && postCache.isCached(postId)){
            postDataStore = new DiskPostDataStore(postCache);
        } else {
            postDataStore = createCloudDataStore();
        }

        return postDataStore;
    }

    public PostDataStore createCloudDataStore(){

        return new CloudPostDataStore(postApiService, postCache);
    }
}
