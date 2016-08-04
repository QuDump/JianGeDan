package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.PostListFragmentModule;
import com.qudump.jiangedan.ui.fragment.PostListFragment;

import dagger.Subcomponent;

/**
 * Created by qidong on 2016/8/4.
 */
@Subcomponent(modules = PostListFragmentModule.class)
public interface PostListFragmentComponent {
    void inject(PostListFragment fragment);
}
