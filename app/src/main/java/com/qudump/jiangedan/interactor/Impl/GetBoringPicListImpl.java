package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.GetBoringPicList;
import com.qudump.jiangedan.model.BoringPic;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.boringpic.BoringPicRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by qidong on 2016/8/8.
 */
public class GetBoringPicListImpl extends UseCase<List<BoringPic>> implements GetBoringPicList {
    private BoringPicRepository repository;
    private int page;

    @Inject
    public GetBoringPicListImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BoringPicRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public GetBoringPicList setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    protected Observable<List<BoringPic>> buildUseCaseObservable() {
        Observable<List<BoringPic>> observable = repository.boringPics(page);
        return observable;
    }
}
