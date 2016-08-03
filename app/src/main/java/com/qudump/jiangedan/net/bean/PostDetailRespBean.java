package com.qudump.jiangedan.net.bean;

/**
 * Created by dili on 2016/8/3.
 */
public class PostDetailRespBean extends BaseResp{
    private String next_url;
    private String previous_url;
    private PostBean post;

    public String getNext_url() {
        return next_url;
    }

    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }

    public String getPrevious_url() {
        return previous_url;
    }

    public void setPrevious_url(String previous_url) {
        this.previous_url = previous_url;
    }

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }
}
