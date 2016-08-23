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
//    private Thread thread;
//
//    public Thread getThread() {
//        return thread;
//    }
//
//    public void setThread(Thread thread) {
//        this.thread = thread;
//    }

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

    public class Thread {
        private String url;
        private String thread_id;
        private String thread_key;
        private String source;
        private String created_at;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
