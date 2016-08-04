package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.PostDetailActivityModule;
import com.qudump.jiangedan.injection.module.PostListFragmentModule;
import com.qudump.jiangedan.injection.module.PostModule;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by qidong on 2016/8/4.
 */
@Subcomponent(modules = PostModule.class)
public interface PostComponent {
    PostListFragmentComponent plus(PostListFragmentModule postListFragmentModule);
    PostDetailActivityComponent plus(PostDetailActivityModule postDetailActivityModule);
}
