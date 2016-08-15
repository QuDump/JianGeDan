package com.qudump.jiangedan.net.bean;

/**
 * Created by dili on 2016/8/15.
 */
public class DuoshuoCommentBean {

    public static final String CREATE_AT = "created_at";
    public static final String LIKE = "likes";
    public static final String DISLIKE = "dislikes";
    public static final String MESSAGE = "message";
    public static final String POST_ID = "post_id";
    public static final String PARENT_ID = "parent_id";
    public static final String THREAD_ID = "thread_id";
    public static final String AUTHOR = "author";

    private String created_at;
    private int like;
    private int dislike;
    private String message;
    private String post_id;
    private String parent_id;
    private String thread_id;
    private Author author;
    private boolean isHotPost;

    public boolean isHotPost() {
        return isHotPost;
    }

    public void setHotPost(boolean hotPost) {
        isHotPost = hotPost;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public static class Author {
        private String avatar_url;
        private int comments;
        private String name;
        private long user_id;
        private String weibo_uid;
        private String qq_uid;

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public String getWeibo_uid() {
            return weibo_uid;
        }

        public void setWeibo_uid(String weibo_uid) {
            this.weibo_uid = weibo_uid;
        }

        public String getQq_uid() {
            return qq_uid;
        }

        public void setQq_uid(String qq_uid) {
            this.qq_uid = qq_uid;
        }
    }
}
