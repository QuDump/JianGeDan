package com.qudump.jiangedan.net.service.comment;

import com.alibaba.fastjson.JSON;
import com.qudump.jiangedan.exception.NetworkConnectionException;
import com.qudump.jiangedan.net.bean.CommentBean;
import com.qudump.jiangedan.net.bean.CommentNumberBean;
import com.qudump.jiangedan.net.bean.DuoshuoCommentBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by dili on 2016/8/12.
 */
public class CommentApiServiceImpl implements CommentApiService {

    private Retrofit retrofit;
    @Inject
    public CommentApiServiceImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<List<CommentNumberBean>> commentNumbers(String params) {
        List<CommentNumberBean> commentNumbers = new ArrayList<>();
        return retrofit
                .create(CommentService.class)
                .commentNumbers(params)
                .flatMap(resp->{
                    if(resp.getCode() != 0) {
                        return Observable.error(new Throwable());
                    } else {
                        try {
                            String jsonStr = resp.getResponse();
                            JSONObject jsonObject = new JSONObject(jsonStr);
                            String[] commentIDs = params.split("\\,");

                            for(String Id:commentIDs) {
                                if(!jsonObject.isNull(Id)) {
                                    CommentNumberBean bean = new CommentNumberBean();
                                    if(jsonObject.getJSONObject(Id).has(CommentNumberBean.COMMENTS)) {
                                        bean.setComments(jsonObject.getJSONObject(Id).getInt(CommentNumberBean.COMMENTS));
                                    }
                                    if(jsonObject.getJSONObject(Id).has(CommentNumberBean.THREAD_ID)) {
                                        bean.setThread_id(jsonObject.getJSONObject(Id).getString(CommentNumberBean.THREAD_ID));
                                    }
                                    if(jsonObject.getJSONObject(Id).has(CommentNumberBean.THREAD_KEY)) {
                                        bean.setThread_key(jsonObject.getJSONObject(Id).getString(CommentNumberBean.THREAD_KEY));
                                    }
                                    commentNumbers.add(bean);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return Observable.just(commentNumbers);
                    }
        });
    }

    @Override
    public Observable<List<DuoshuoCommentBean>> comments(String commentId) {
        List<DuoshuoCommentBean> commentBeanList = new ArrayList<>();
        return retrofit
                .create(CommentService.class)
                .comments(commentId)
                .flatMap(resp->{
                    if(0 != resp.getCode()) {
                        return Observable.error(new Throwable());
                    } else {
                        try {
                            String jsonStr = resp.getParentPosts();
                            JSONObject jsonObject = new JSONObject(jsonStr);
                            String[] hotPosts = resp.getHotPosts();

                            for(String id:resp.getResponse()) {
                                if(!jsonObject.isNull(id)) {
                                    DuoshuoCommentBean bean = new DuoshuoCommentBean();
                                    for(String postId:hotPosts) {
                                        if(postId.equals(id)) {
                                            bean.setHotPost(true);
                                            break;
                                        }
                                    }
                                    if(jsonObject.getJSONObject(id).has(DuoshuoCommentBean.CREATE_AT)) {
                                        bean.setCreated_at(jsonObject.getJSONObject(id).getString(DuoshuoCommentBean.CREATE_AT));
                                    }
                                    if(jsonObject.getJSONObject(id).has(DuoshuoCommentBean.LIKE)) {
                                        bean.setLike(jsonObject.getJSONObject(id).getInt(DuoshuoCommentBean.LIKE));
                                    }
                                    if(jsonObject.getJSONObject(id).has(DuoshuoCommentBean.DISLIKE)) {
                                        bean.setDislike(jsonObject.getJSONObject(id).getInt(DuoshuoCommentBean.DISLIKE));
                                    }
                                    if(jsonObject.getJSONObject(id).has(DuoshuoCommentBean.MESSAGE)) {
                                        bean.setMessage(jsonObject.getJSONObject(id).getString(DuoshuoCommentBean.MESSAGE));
                                    }
                                    if(jsonObject.getJSONObject(id).has(DuoshuoCommentBean.POST_ID)) {
                                        bean.setPost_id(jsonObject.getJSONObject(id).getString(DuoshuoCommentBean.POST_ID));
                                    }
                                    if(jsonObject.getJSONObject(id).has(DuoshuoCommentBean.PARENT_ID)) {
                                        bean.setParent_id(jsonObject.getJSONObject(id).getString(DuoshuoCommentBean.PARENT_ID));
                                    }
                                    if(jsonObject.getJSONObject(id).has(DuoshuoCommentBean.THREAD_ID)) {
                                        bean.setThread_id(jsonObject.getJSONObject(id).getString(DuoshuoCommentBean.THREAD_ID));
                                    }
                                    if(jsonObject.getJSONObject(id).has(DuoshuoCommentBean.AUTHOR)) {
                                        String author = jsonObject.getJSONObject(id).getString(DuoshuoCommentBean.AUTHOR);
                                        DuoshuoCommentBean.Author authorbean = JSON.parseObject(author, DuoshuoCommentBean.Author.class);
                                        bean.setAuthor(authorbean);
                                    }

                                    commentBeanList.add(bean);
                                }
                            }
                        } catch (JSONException e) {
                            return Observable.error(new Throwable("convert error"));
                        }
                        return Observable.just(commentBeanList);
                    }
                });
    }

    @Override
    public Observable<List<CommentBean>> postComments(long id) {
        return retrofit
                .create(CommentService.class)
                .getPostComments(id)
                .flatMap(resp->{
                    if(resp ==null || !resp.getStatus().equals("ok")){
                        return Observable.error(new NetworkConnectionException());
                    }
                    return Observable.just(resp.getPost().getComments());
                });
    }

    @Override
    public Observable<String> like(long id) {
        return retrofit
                .create(CommentService.class)
                .like(id)
                .flatMap(resp->{
                    String[] result = null;
                    try {
                        String body = resp.body().string();
                        result = body.split("|");
                    } catch (IOException e) {
                        Observable.error(new Throwable("Unexpected Error"));
                        e.printStackTrace();
                    }

                    return Observable.just(result[1]);
                });
    }

    @Override
    public Observable<String> dislike(long id) {
        return retrofit
                .create(CommentService.class)
                .dislike(id)
                .flatMap(resp->{
                    String[] result = null;
                    try {
                        String body = resp.body().string();
                        result = body.split("|");
                    } catch (IOException e) {
                        Observable.error(new Throwable("Unexpected Error"));
                        e.printStackTrace();
                    }
                    return Observable.just(result[1]);
                });
    }

    @Override
    public Observable<Boolean> writeComment(Map<String, String> param) {

        return retrofit
                .create(CommentService.class)
                .writeComment(param)
                .flatMap(resp->{
                    if(resp.getCode() != 0){
                        return Observable.error(new Throwable("network error"));
                    }
                    return Observable.just(true);
                });
    }
}
