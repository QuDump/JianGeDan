package com.qudump.jiangedan.repository;

import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.net.service.PostService;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by dili on 2016/8/3.
 */
public class PostRepository {
    private Retrofit retrofit;

    public PostRepository(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Observable<List<Post>> getPostList(){
        return retrofit
                .create(PostService.class)
                .getPosts()
                .flatMap()
    }
}
