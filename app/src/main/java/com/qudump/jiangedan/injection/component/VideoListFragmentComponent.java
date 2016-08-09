package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.VideoListFragmentModule;
import com.qudump.jiangedan.ui.fragment.VideoListFragment;

import dagger.Subcomponent;

/**
 * Created by dili on 2016/8/9.
 */
@Subcomponent(modules = VideoListFragmentModule.class)
public interface VideoListFragmentComponent {
    void inject(VideoListFragment fragment);
}
