package com.qudump.jiangedan.net.bean;

import java.util.List;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicListRespBean extends BaseResp {

    private List<BoringPicBean> comments;
    private int count;
    private int current_page;
    private int page_count;
    private int total_comments;

    public List<BoringPicBean> getComments() {
        return comments;
    }

    public void setComments(List<BoringPicBean> comments) {
        this.comments = comments;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getTotal_comments() {
        return total_comments;
    }

    public void setTotal_comments(int total_comments) {
        this.total_comments = total_comments;
    }
}
