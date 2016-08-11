package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.interactor.GetPostComments;
import com.qudump.jiangedan.interactor.Impl.GetPostCommentsImpl;
import com.qudump.jiangedan.presenter.PostCommentListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dili on 2016/8/11.
 */
@Module
public class PostCommentListActivityModule {
    @Provides
    GetPostComments providesGetPostComments(GetPostCommentsImpl getPostComments) {
        return getPostComments;
    }

    @Provides
    PostCommentListPresenter providesPostCommentListPresenter(GetPostComments getPostComments) {
        return new PostCommentListPresenter(getPostComments);
    }
}
