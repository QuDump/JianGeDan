package com.qudump.jiangedan.presenter;

/**
 * Created by dili on 2016/8/3.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
    void showErrMsg(String msg);
}
