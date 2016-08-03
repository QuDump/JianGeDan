package com.qudump.jiangedan.interactor;

import rx.Subscriber;

/**
 * Created by dili on 2016/8/3.
 */
public interface Interactor {

    void execute(Subscriber useCaseSubscriber);

    void unsubscribe();
}
