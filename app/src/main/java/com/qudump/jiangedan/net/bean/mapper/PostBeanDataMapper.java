package com.qudump.jiangedan.net.bean.mapper;

import android.support.annotation.NonNull;

import com.qudump.jiangedan.model.Author;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.net.bean.AuthorBean;
import com.qudump.jiangedan.net.bean.PostBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dili on 2016/8/4.
 */
public class PostBeanDataMapper {

    @Inject
    public PostBeanDataMapper() {
    }

    public Post transform(PostBean postBean) {
        Post post = null;
        if(postBean != null) {
            post = new Post();
            post.setId(postBean.getId());
            post.setDate(postBean.getDate());
            post.setCommentCount(postBean.getComment_count());
            post.setThumbImg(postBean.getCustom_fields().getThumb_c());
            post.setAuthor(getAuthor(postBean.getAuthorBean()));
            post.setTitle(postBean.getTitle());
            post.setUrl(postBean.getUrl());

        }

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

    public List<Post> transform(Collection<PostBean> postBeanCollection) {
        List<Post> posts = new ArrayList<>();
        Post post;
        for(PostBean item :postBeanCollection) {
            post = transform(item);
            if(post != null) {
             posts.add(post);
            }
        }

        return posts;
    }
}
