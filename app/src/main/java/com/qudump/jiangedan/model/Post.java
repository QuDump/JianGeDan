package com.qudump.jiangedan.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dili on 2016/8/3.
 */
public class Post implements Serializable {
    private long id;
    private String title;
    private String content;
    private String url;
    private String date;
    private List<String> thumbImg;
    private int commentCount;
    private List<String> tags;
    private Author author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(List<String> thumbImg) {
        this.thumbImg = thumbImg;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
