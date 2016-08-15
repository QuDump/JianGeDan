package com.qudump.jiangedan.repository.littlevideo.datasource;

import com.qudump.jiangedan.model.LittleVideo;
import com.qudump.jiangedan.net.bean.CommentNumberBean;

import java.util.List;

import rx.Observable;

/**
 * Created by dili on 2016/8/9.
 */
public interface VideoDataStore {
    Observable<List<LittleVideo>> videos(int page);
    Observable<List<CommentNumberBean>> commentNumbers(String params);
}
