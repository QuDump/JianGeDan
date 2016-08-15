package com.qudump.jiangedan.repository.boringpic;

import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.net.bean.CommentNumberBean;
import com.qudump.jiangedan.repository.boringpic.datasource.BoringPicDataStore;
import com.qudump.jiangedan.repository.boringpic.datasource.BoringPicDataStoreFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicRepository {
    private BoringPicDataStoreFactory boringPicDataStoreFactory;
    private List<BoringPic> mBoringPics;

    @Inject
    public BoringPicRepository(BoringPicDataStoreFactory boringPicDataStoreFactory) {
        this.boringPicDataStoreFactory = boringPicDataStoreFactory;
    }

    public Observable<List<BoringPic>> boringPics(int page) {
        BoringPicDataStore boringPicDataStore = boringPicDataStoreFactory.createCloudDataStore();
        return boringPicDataStore
                .pics(page)
                .doOnNext(boringPics->mBoringPics = boringPics)
                .flatMap(boringPics->queryCommentCounts(boringPicDataStore,getCommentParams(boringPics)))
                .flatMap(commentNumberRespBeen->Observable.just(appendCommentCountToPicModel(mBoringPics,commentNumberRespBeen)));
    }

    private String getCommentParams(List<BoringPic> pics) {
        StringBuilder params = new StringBuilder();
        for(BoringPic pic:pics) {
            String item = "comment-"+pic.getCommentId()+",";
            params.append(item);
        }

        return params.toString();
    }

    private Observable<List<CommentNumberBean>> queryCommentCounts(BoringPicDataStore dataStore, String params) {
        return dataStore.commentNumbers(params);
    }

    private List<BoringPic> appendCommentCountToPicModel(List<BoringPic> pics, List<CommentNumberBean> commentBeans) {
        for(BoringPic pic:pics) {
            for(CommentNumberBean bean:commentBeans){
                if(pic.getCommentId() == bean.getCommentId()) {
                    pic.setComments(bean.getComments());
                    continue;
                }
            }
        }
        return pics;
    }

}
