package com.qudump.jiangedan.model;

import java.util.List;

/**
 * Created by dili on 2016/8/3.
 */
public class GirlPic {
    private long id;
    private long commentId;
    private String authorName;
    private String authorEmail;
    private List<String> pics;
    private String date;
    private int likeCounts;
    private int dislikeCounts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public int getDislikeCounts() {
        return dislikeCounts;
    }

    public void setDislikeCounts(int dislikeCounts) {
        this.dislikeCounts = dislikeCounts;
    }
}
