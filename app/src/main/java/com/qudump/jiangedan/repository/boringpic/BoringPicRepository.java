package com.qudump.jiangedan.repository.boringpic;

import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.net.bean.CommentNumberRespBean;
import com.qudump.jiangedan.repository.boringpic.datasource.BoringPicDataStore;
import com.qudump.jiangedan.repository.boringpic.datasource.BoringPicDataStoreFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicRepository {
    private BoringPicDataStoreFactory boringPicDataStoreFactory;
    private List<BoringPic> mBoringPics;
    private List<CommentNumberRespBean> commentNumberRespBeens;

    @Inject
    public BoringPicRepository(BoringPicDataStoreFactory boringPicDataStoreFactory) {
        this.boringPicDataStoreFactory = boringPicDataStoreFactory;
    }

    public Observable<List<BoringPic>> boringPics(int page) {
        BoringPicDataStore boringPicDataStore = boringPicDataStoreFactory.createCloudDataStore();
        return boringPicDataStore
                .pics(page)
                .doOnNext(new Action1<List<BoringPic>>() {
                    @Override
                    public void call(List<BoringPic> boringPics) {
                        mBoringPics = boringPics;
                    }
                })
                .flatMap(new Func1<List<BoringPic>, Observable<List<CommentNumberRespBean>>>() {
                    @Override
                    public Observable<List<CommentNumberRespBean>> call(List<BoringPic> boringPics) {
                        return queryCommentCounts(boringPicDataStore,getCommentParams(boringPics));
                    }
                })
                .flatMap(new Func1<List<CommentNumberRespBean>, Observable<List<BoringPic>>>() {
                    @Override
                    public Observable<List<BoringPic>> call(List<CommentNumberRespBean> commentNumberRespBeen) {
                        return Observable.just(appendCommentCountToPicModel(mBoringPics,commentNumberRespBeen));
                    }
                });
    }

    private String getCommentParams(List<BoringPic> pics) {
        StringBuilder params = new StringBuilder();
        for(BoringPic pic:pics) {
            String item = "comment-"+pic.getCommentId()+",";
            params.append(item);
        }

        return params.toString();
    }

    private Observable<List<CommentNumberRespBean>> queryCommentCounts(BoringPicDataStore dataStore,String params) {
        return dataStore.comments(params);
    }

    private List<BoringPic> appendCommentCountToPicModel(List<BoringPic> pics, List<CommentNumberRespBean> commentBeans) {
        for(BoringPic pic:pics) {
            for(CommentNumberRespBean bean:commentBeans){
                if(pic.getCommentId() == bean.getCommentId()) {
                    pic.setComments(bean.getComments());
                    continue;
                }
            }
        }
        return pics;
    }

}
