package com.qudump.rxbus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by dell on 2016/8/26.
 */
public class RxBus {
    private static volatile RxBus defaultInstance;
    private final Subject<Object, Object> bus;
    private Subscription subscription;
    private Observable observable;
    private final Map<Class<?>, Object> mStickyEventMap;

    private RxBus(){
        bus = new SerializedSubject<>(PublishSubject.create());
        mStickyEventMap = new ConcurrentHashMap<>();
    }

    public static RxBus getDefault(){
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    public void post(Object event) {
        bus.onNext(event);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public<T> Observable<T> toObservable (Class<T> eventType) {
        observable = bus.ofType(eventType);
        return observable;
    }

    public <T> Observable<T> threadMode(ThreadMode mode){

        switch (mode){
            case POSTING:
                return observable.observeOn(Schedulers.immediate());
            case MAIN:
                return observable.observeOn(AndroidSchedulers.mainThread());
            case BACKGROUND:
                return observable.observeOn(Schedulers.newThread());
            case ASYNC:
                return observable.observeOn(Schedulers.newThread());
            default:
                return observable.observeOn(Schedulers.immediate());
        }
    }

    public void postSticky(Object event){
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(event.getClass(), event);
        }
        post(event);
    }

    public <T> Observable<T> toObservableSticky(final Class<T> eventType) {
        synchronized (mStickyEventMap) {
            Observable<T> observable = bus.ofType(eventType);
            final Object event = mStickyEventMap.get(eventType);

            if (event != null) {
                return observable.mergeWith(Observable.create(new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        subscriber.onNext(eventType.cast(event));
                    }
                }));
            } else {
                return observable;
            }
        }
    }
}
