package com.qudump.jiangedan.repository.girls;

import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.net.bean.CommentNumberRespBean;
import com.qudump.jiangedan.repository.girls.datasource.GirlPicDataStore;
import com.qudump.jiangedan.repository.girls.datasource.GirlPicDataStoreFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public class GirlPicRepository {
    GirlPicDataStoreFactory girlPicDataStoreFactory;
    private List<GirlPic> mGirlPics;

    @Inject
    public GirlPicRepository(GirlPicDataStoreFactory girlPicDataStoreFactory) {
        this.girlPicDataStoreFactory = girlPicDataStoreFactory;
    }

    public Observable<List<GirlPic>> girlPics(int page) {
        GirlPicDataStore girlPicDataStore = girlPicDataStoreFactory.createCloudDataStore();
        return girlPicDataStore
                .girlPics(page)
                .doOnNext(girlPics-> mGirlPics = girlPics)
                .flatMap(girlPics->queryCommentCounts(girlPicDataStore,getCommentParams(girlPics)))
                .flatMap(commentNumbers->Observable.just(appendCommentCountToPicModel(mGirlPics,commentNumbers)));
    }

    private String getCommentParams(List<GirlPic> pics) {
        StringBuilder params = new StringBuilder();
        for(GirlPic pic:pics) {
            String item = "comment-"+pic.getCommentId()+",";
            params.append(item);
        }

        return params.toString();
    }

    private Observable<List<CommentNumberRespBean>> queryCommentCounts(GirlPicDataStore dataStore, String params) {
        return dataStore.commentNumbers(params);
    }

    private List<GirlPic> appendCommentCountToPicModel(List<GirlPic> pics, List<CommentNumberRespBean> commentBeans) {
        for(GirlPic pic:pics) {
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
