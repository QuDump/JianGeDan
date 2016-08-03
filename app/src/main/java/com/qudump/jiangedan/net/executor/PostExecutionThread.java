package com.qudump.jiangedan.net.executor;

import rx.Scheduler;

/**
 * Created by dili on 2016/8/3.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
