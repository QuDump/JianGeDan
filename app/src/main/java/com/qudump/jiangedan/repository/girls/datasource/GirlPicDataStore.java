package com.qudump.jiangedan.repository.girls.datasource;

import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.net.bean.CommentNumberRespBean;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public interface GirlPicDataStore {
    Observable<List<GirlPic>> girlPics(int page);
    Observable<List<CommentNumberRespBean>> commentNumbers(String params);
}
