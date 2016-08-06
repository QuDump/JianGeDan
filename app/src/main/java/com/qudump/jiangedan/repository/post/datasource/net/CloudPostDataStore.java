package com.qudump.jiangedan.repository.post.datasource.net;

import com.qudump.jiangedan.cache.PostCache;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.net.bean.mapper.PostBeanDataMapper;
import com.qudump.jiangedan.net.service.post.PostApiService;
import com.qudump.jiangedan.repository.post.datasource.PostDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dili on 2016/8/4.
 */
public class CloudPostDataStore implements PostDataStore {

    private PostApiService postApiService;
    private PostCache postCache;
    private PostBeanDataMapper postBeanDataMapper;
    private Action1<Post> saveToCacheAction = post -> {
        if (post != null) {
            postCache.put(post);
        }
    };

    @Inject
    public CloudPostDataStore(PostApiService postApiService, PostCache postCache, PostBeanDataMapper postBeanDataMapper) {
        this.postApiService = postApiService;
        this.postCache = postCache;
        this.postBeanDataMapper = postBeanDataMapper;
    }

    @Override
    public Observable<List<Post>> postList(final int page) {

        return postApiService.postList(page).map(postBeanDataMapper::transform);
    }

    @Override
    public Observable<Post> postDetail(final long id) {

        return postApiService.postDetailById(id).doOnNext(saveToCacheAction);
    }
}
