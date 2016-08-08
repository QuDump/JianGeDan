package com.qudump.jiangedan.repository.boringpic.datasource;

import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.repository.BaseDataStoreFactory;
import com.qudump.jiangedan.repository.girls.datasource.GirlPicDataStore;

import java.util.List;

import rx.Observable;

/**
 * Created by qidong on 2016/8/8.
 */
public interface BoringPicDataStore {
    Observable<List<BoringPic>> pics(int page);
}
