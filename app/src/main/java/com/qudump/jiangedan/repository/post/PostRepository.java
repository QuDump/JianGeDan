package com.qudump.jiangedan.repository.post;

import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.repository.post.datasource.PostDataStore;
import com.qudump.jiangedan.repository.post.datasource.PostDataStoreFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/3.
 */
public class PostRepository {

    private PostDataStoreFactory postDataStoreFactory;

    @Inject
    public PostRepository(PostDataStoreFactory postDataStoreFactory) {
        this.postDataStoreFactory = postDataStoreFactory;
    }

    public Observable<List<Post>> posts(int page){
        PostDataStore postDataStore = postDataStoreFactory.createCloudDataStore();
        return postDataStore.postList(page);
    }

    public Observable<Post> post(long postId) {
        PostDataStore postDataStore = postDataStoreFactory.create(postId);
        Observable<Post> postObservable = postDataStore.postDetail(postId);
        return postObservable;
    }

}
