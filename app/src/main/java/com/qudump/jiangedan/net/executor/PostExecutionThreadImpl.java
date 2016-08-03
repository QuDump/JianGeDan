package com.qudump.jiangedan.net.executor;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by dili on 2016/8/3.
 */
public class PostExecutionThreadImpl implements PostExecutionThread {
    @Inject
    public PostExecutionThreadImpl() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
