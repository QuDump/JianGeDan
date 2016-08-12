package com.qudump.jiangedan.repository.boringpic.datasource;

import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.net.bean.CommentNumberRespBean;

import java.util.List;

import rx.Observable;

/**
 * Created by qidong on 2016/8/8.
 */
public interface BoringPicDataStore {
    Observable<List<BoringPic>> pics(int page);
    Observable<List<CommentNumberRespBean>> comments(String params);
}
