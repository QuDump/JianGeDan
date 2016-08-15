package com.qudump.jiangedan.net.bean;

/**
 * Created by dili on 2016/8/12.
 */
public class CommentNumberBean extends BaseResp {
    public static final String COMMENTS = "comments";

    public static final String THREAD_ID = "thread_id";
    public static final String THREAD_KEY = "thread_key";

    private int comments;
    private String thread_id;
    private String thread_key;
    private long commentId;

    public CommentNumberBean() {
    }

    public CommentNumberBean(int comments, String thread_id, String thread_key) {
        this.comments = comments;
        this.thread_id = thread_id;
        this.thread_key = thread_key;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getThread_key() {
        return thread_key;
    }

    public void setThread_key(String thread_key) {
        this.thread_key = thread_key;
        setCommentId(Long.valueOf(thread_key.substring(8,thread_key.length())));
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
}
