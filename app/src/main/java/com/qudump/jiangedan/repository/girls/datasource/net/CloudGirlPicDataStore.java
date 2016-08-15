package com.qudump.jiangedan.repository.girls.datasource.net;

import com.qudump.jiangedan.cache.GirlPicCache;
import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.net.bean.CommentNumberBean;
import com.qudump.jiangedan.net.bean.mapper.GirlPicBeanMapper;
import com.qudump.jiangedan.net.service.comment.CommentApiService;
import com.qudump.jiangedan.net.service.girlpic.GirlPicApiService;
import com.qudump.jiangedan.repository.girls.datasource.GirlPicDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by dili on 2016/8/8.
 */
public class CloudGirlPicDataStore implements GirlPicDataStore {
    private GirlPicApiService apiService;
    private CommentApiService commentApiService;
    private GirlPicBeanMapper mapper;
    private GirlPicCache girlPicCache;
    private Action1<GirlPic> savaToCacheAction = girlPic -> {
        if(null != girlPic) {
            girlPicCache.put(girlPic);
        }
    };

    @Inject
    public CloudGirlPicDataStore(GirlPicApiService apiService, CommentApiService commentApiService, GirlPicBeanMapper mapper, GirlPicCache girlPicCache) {
        this.apiService = apiService;
        this.commentApiService = commentApiService;
        this.mapper = mapper;
        this.girlPicCache = girlPicCache;
    }

    @Override
    public Observable<List<GirlPic>> girlPics(int page) {
        return apiService.girlPics(page).map(mapper::transform);
    }

    @Override
    public Observable<List<CommentNumberBean>> commentNumbers(String params) {
        return commentApiService.commentNumbers(params);
    }
}
