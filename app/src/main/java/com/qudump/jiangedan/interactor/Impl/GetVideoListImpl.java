package com.qudump.jiangedan.interactor.Impl;

import com.qudump.jiangedan.interactor.GetVideoList;
import com.qudump.jiangedan.model.LittleVideo;
import com.qudump.jiangedan.net.executor.PostExecutionThread;
import com.qudump.jiangedan.net.executor.ThreadExecutor;
import com.qudump.jiangedan.repository.littlevideo.VideoRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/9.
 */
public class GetVideoListImpl extends UseCase<List<LittleVideo>> implements GetVideoList {

    private VideoRepository repository;
    private int page;

    @Inject
    public GetVideoListImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, VideoRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public GetVideoList setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    protected Observable<List<LittleVideo>> buildUseCaseObservable() {
        return repository.videos(page);
    }
}
