package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.CommentListActivityModule;
import com.qudump.jiangedan.ui.CommentListActivity;

import dagger.Subcomponent;

/**
 * Created by dili on 2016/8/15.
 */
@Subcomponent(modules = {CommentListActivityModule.class})
public interface CommentListActivityComponent {
    void inject(CommentListActivity activity);
}
