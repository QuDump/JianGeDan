package com.qudump.jiangedan.net.bean;

import java.util.List;

/**
 * Created by dili on 2016/8/3.
 */
public class PostBean {
    private long id;
    private String content;
    private String url;
    private String title;
    private String date;
    private List<TagBean> tagBeen;
    private AuthorBean authorBean;
    private int comment_count;
    private CustomField custom_fields;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TagBean> getTagBeen() {
        return tagBeen;
    }

    public void setTagBeen(List<TagBean> tagBeen) {
        this.tagBeen = tagBeen;
    }

    public AuthorBean getAuthorBean() {
        return authorBean;
    }

    public void setAuthorBean(AuthorBean authorBean) {
        this.authorBean = authorBean;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public CustomField getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(CustomField custom_fields) {
        this.custom_fields = custom_fields;
    }

    public static class CustomField {
        private List<String> thumb_c;

        public List<String> getThumb_c() {
            return thumb_c;
        }

        public void setThumb_c(List<String> thumb_c) {
            this.thumb_c = thumb_c;
        }
    }
}
