package com.qudump.jiangedan.repository;

/**
 * Created by dili on 2016/8/8.
 */
public interface BaseDataStoreFactory<T> {
    T create(long id);
    T createCloudDataStore();
}
