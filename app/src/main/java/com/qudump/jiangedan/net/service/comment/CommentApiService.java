package com.qudump.jiangedan.net.service.comment;

import com.qudump.jiangedan.net.bean.CommentNumberRespBean;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/12.
 */
public interface CommentApiService {
    Observable<List<CommentNumberRespBean>> commentNumbers(String params);
}
