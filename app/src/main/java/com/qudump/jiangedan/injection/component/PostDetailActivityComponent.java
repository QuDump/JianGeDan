package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.PostDetailActivityModule;
import com.qudump.jiangedan.ui.PostDetailActivity;

import dagger.Subcomponent;

/**
 * Created by qidong on 2016/8/4.
 */
@Subcomponent(modules = PostDetailActivityModule.class)
public interface PostDetailActivityComponent {
    void inject(PostDetailActivity postDetailActivity);
}
