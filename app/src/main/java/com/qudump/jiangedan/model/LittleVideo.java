package com.qudump.jiangedan.model;

/**
 * Created by dili on 2016/8/9.
 */
public class LittleVideo {

    private long id;
    private long commentId;
    private String authorName;
    private String authorEmail;
    private String date;
    private int likeCounts;
    private int dislikeCounts;
    private String title;
    private String webPageLink;
    private String videoLink;
    private String description;
    private String tags;
    private String thumbNail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebPageLink() {
        return webPageLink;
    }

    public void setWebPageLink(String webPageLink) {
        this.webPageLink = webPageLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
