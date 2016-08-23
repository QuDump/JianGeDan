package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.interactor.GetOtherComments;
import com.qudump.jiangedan.interactor.GetPostComments;
import com.qudump.jiangedan.interactor.Impl.GetOtherCommentsImpl;
import com.qudump.jiangedan.interactor.Impl.GetPostCommentsImpl;
import com.qudump.jiangedan.interactor.Impl.PostCommentImpl;
import com.qudump.jiangedan.interactor.PostComment;
import com.qudump.jiangedan.presenter.CommentListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dili on 2016/8/15.
 */
@Module
public class CommentListActivityModule {
    @Provides
    GetPostComments providesGetPostComments(GetPostCommentsImpl getPostComments) {
        return getPostComments;
    }

    @Provides
    GetOtherComments providesGetOtherComments(GetOtherCommentsImpl getOtherComments) {
        return getOtherComments;
    }

    @Provides
    CommentListPresenter providesPostCommentListPresenter(GetPostComments getPostComments, GetOtherComments getOtherComments,PostComment postComment) {
        return new CommentListPresenter(getPostComments,getOtherComments,postComment);
    }

    @Provides
    PostComment providesPostComment(PostCommentImpl postComment) {
        return postComment;
    }
}
