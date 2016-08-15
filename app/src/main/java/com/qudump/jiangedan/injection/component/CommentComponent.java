package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.CommentListActivityModule;
import com.qudump.jiangedan.injection.module.CommentsModule;

import dagger.Subcomponent;

/**
 * Created by dili on 2016/8/15.
 */
@Subcomponent(modules = {CommentsModule.class})
public interface CommentComponent {
    CommentListActivityComponent plus(CommentListActivityModule module);
}
