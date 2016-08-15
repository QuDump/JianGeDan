package com.qudump.jiangedan.net.bean;

/**
 * Created by dili on 2016/8/15.
 */
public class DuoshuoCommentsRespBean {
    private int code;
    private Cursor cursor;
    private String[] hotPosts;
    private String parentPosts;
    private String[] response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public String[] getHotPosts() {
        return hotPosts;
    }

    public void setHotPosts(String[] hotPosts) {
        this.hotPosts = hotPosts;
    }

    public String getParentPosts() {
        return parentPosts;
    }

    public void setParentPosts(String parentPosts) {
        this.parentPosts = parentPosts;
    }

    public String[] getResponse() {
        return response;
    }

    public void setResponse(String[] response) {
        this.response = response;
    }

    public class Cursor {
        private int page;
        private int total;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
