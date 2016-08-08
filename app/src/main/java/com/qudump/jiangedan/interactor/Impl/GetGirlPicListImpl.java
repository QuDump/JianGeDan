package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.GetGirlPicList;
import com.qudump.jiangedan.model.GirlPic;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.girls.GirlPicRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/8.
 */
public class GetGirlPicListImpl extends UseCase implements GetGirlPicList {
    private GirlPicRepository repository;
    private int page;

    @Inject
    public GetGirlPicListImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, GirlPicRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public GetGirlPicList setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    protected Observable<List<GirlPic>> buildUseCaseObservable() {
        return repository.girlPics(page);
    }
}
