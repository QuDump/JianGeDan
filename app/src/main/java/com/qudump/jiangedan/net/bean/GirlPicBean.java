package com.qudump.jiangedan.net.bean;

import java.util.List;

/**
 * Created by dili on 2016/8/8.
 */
public class GirlPicBean {
    private long comment_post_ID;
    private long comment_ID;
    private String comment_author;
    private String comment_author_email;
    private List<String> pics;
    private String comment_date;

    private int vote_negative;
    private int vote_positive;
    private int comment_approved;

    public int getComment_approved() {
        return comment_approved;
    }

    public void setComment_approved(int comment_approved) {
        this.comment_approved = comment_approved;
    }

    public long getComment_post_ID() {
        return comment_post_ID;
    }

    public void setComment_post_ID(long comment_post_ID) {
        this.comment_post_ID = comment_post_ID;
    }

    public long getComment_ID() {
        return comment_ID;
    }

    public void setComment_ID(long comment_ID) {
        this.comment_ID = comment_ID;
    }

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public String getComment_author_email() {
        return comment_author_email;
    }

    public void setComment_author_email(String comment_author_email) {
        this.comment_author_email = comment_author_email;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public int getVote_negative() {
        return vote_negative;
    }

    public void setVote_negative(int vote_negative) {
        this.vote_negative = vote_negative;
    }

    public int getVote_positive() {
        return vote_positive;
    }

    public void setVote_positive(int vote_positive) {
        this.vote_positive = vote_positive;
    }
}
