package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.PostCommentListActivityModule;
import com.qudump.jiangedan.ui.CommentListActivity;

import dagger.Subcomponent;

/**
 * Created by dili on 2016/8/11.
 */
@Subcomponent(modules = PostCommentListActivityModule.class)
public interface PostCommentListComponent {
    void inject(CommentListActivity activity);
}
