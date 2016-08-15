package com.qudump.jiangedan.net.service.post;

import com.qudump.jiangedan.exception.NetworkConnectionException;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.net.bean.PostBean;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by dili on 2016/8/4.
 */
public class PostApiServiceImpl implements PostApiService {

    private Retrofit retrofit;

    @Inject
    public PostApiServiceImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<List<PostBean>> postList(int page) {

        return retrofit
                .create(PostService.class)
                .getPosts(String.valueOf(page))
                .flatMap(resp->{
                    if(resp ==null || !resp.getStatus().equals("ok")){
                        return Observable.error(new NetworkConnectionException());
                    }
                    return Observable.just(resp.getPosts());
                });
    }

    @Override
    public Observable<Post> postDetailById(long id) {

        return retrofit
                .create(PostService.class)
                .getPostDetail(id)
                .flatMap(resp->{
                    if(resp ==null || !resp.getStatus().equals("ok")){
                        return Observable.error(new NetworkConnectionException());
                    }
                    return Observable.just(resp.getPost());
                })
                .map(resp->{
                    Post post = new Post();
                    post.setId(id);
                    post.setContent(resp.getContent());
                    return post;
                });
    }


}
