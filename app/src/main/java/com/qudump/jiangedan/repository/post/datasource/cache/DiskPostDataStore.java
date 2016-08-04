package com.qudump.jiangedan.repository.post.datasource.cache;

import com.qudump.jiangedan.cache.PostCache;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.repository.post.datasource.PostDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/4.
 */
public class DiskPostDataStore implements PostDataStore{

    private PostCache postCache;

    @Inject
    public DiskPostDataStore(PostCache postCache) {
        this.postCache = postCache;
    }

    @Override
    public Observable<Post> postDetail(long id) {
        return postCache.get(id);
    }

    @Override
    public Observable<List<Post>> postList(int page) {
        throw new UnsupportedOperationException("operation is not avaliable");
    }
}
