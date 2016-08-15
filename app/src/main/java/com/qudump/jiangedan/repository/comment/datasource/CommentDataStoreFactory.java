package com.qudump.jiangedan.repository.comment.datasource;

import com.qudump.jiangedan.repository.BaseDataStoreFactory;
import com.qudump.jiangedan.repository.comment.datasource.net.CloudCommentDataStore;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by dili on 2016/8/15.
 */
public class CommentDataStoreFactory implements BaseDataStoreFactory<CommentDataStore> {

    @Inject
    Lazy<CloudCommentDataStore> cloudCommentDataStoreLazy;

    @Inject
    public CommentDataStoreFactory() {
    }

    @Override
    public CommentDataStore create(long id) {
        return null;
    }

    @Override
    public CommentDataStore createCloudDataStore() {
        return cloudCommentDataStoreLazy.get();
    }
}
