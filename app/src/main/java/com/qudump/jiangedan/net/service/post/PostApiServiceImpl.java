package com.qudump.jiangedan.net.service.post;

import android.support.annotation.NonNull;

import com.qudump.jiangedan.exception.NetworkConnectionException;
import com.qudump.jiangedan.model.Author;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.net.bean.AuthorBean;
import com.qudump.jiangedan.net.bean.PostBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Observable<List<Post>> postList(int page) {

        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("oxwlxojflwblxbsapi","get_recent_post");
        queryMap.put("include","url,date,tags,author,title,comment_count,custom_fields");
        queryMap.put("page",String.valueOf(page));
        queryMap.put("custom_fields","thumb_c,views");
        queryMap.put("dev","1");

        return retrofit
                .create(PostService.class)
                .getPosts(queryMap)
                .flatMap(resp->{
                    if(resp ==null || !resp.getStatus().equals("ok")){
                        return Observable.error(new NetworkConnectionException());
                    }
                    return Observable.just(resp);
                }).map(resp->{
                    List<Post> posts = new ArrayList<>();
                    for(PostBean item:resp.getPosts()){
                        posts.add(mapBeanToModel(item));
                    }
                    return posts;
                });
    }

    @Override
    public Observable<Post> postDetailById(long id) {
        String api = "get_post";
        String param = "content";

        return retrofit
                .create(PostService.class)
                .getPostDetail(api,id,param)
                .flatMap(resp->{
                    if(resp ==null || !resp.getStatus().equals("ok")){
                        return Observable.error(new NetworkConnectionException());
                    }
                    return Observable.just(resp);
                })
                .map(resp->{
                    Post post = new Post();
                    post.setId(id);
                    post.setContent(resp.getPost().getContent());
                    return post;
                });
    }

    private Post mapBeanToModel(PostBean item) {
        Post post = new Post();
        post.setId(item.getId());
        post.setDate(item.getDate());
        post.setCommentCount(item.getComment_count());
        post.setThumbImg(item.getCustom_fields().getThumb_c());
        post.setAuthor(getAuthor(item.getAuthorBean()));
        post.setTitle(item.getTitle());
        post.setUrl(item.getUrl());

        return post;
    }

    @NonNull
    private Author getAuthor(AuthorBean item) {
        Author author = new Author();
        author.setId(item.getId());
        author.setName(item.getName());
        author.setUrl(item.getUrl());
        author.setDescription(item.getDescription());
        author.setNickname(item.getNickname());
        author.setFirstName(item.getFirst_name());
        author.setLastName(item.getLast_name());
        author.setSlug(item.getSlug());

        return author;
    }


}
