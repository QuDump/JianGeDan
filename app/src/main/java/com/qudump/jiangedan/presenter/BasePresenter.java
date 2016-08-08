package com.qudump.jiangedan.presenter;

/**
 * Created by dili on 2016/8/3.
 */
public interface BasePresenter<T> {
    void setView(T view);
    void start();
    void destroy();
}
