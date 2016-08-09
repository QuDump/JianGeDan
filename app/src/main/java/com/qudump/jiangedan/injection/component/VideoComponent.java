package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.VideoListFragmentModule;
import com.qudump.jiangedan.injection.module.VideoModule;

import dagger.Subcomponent;

/**
 * Created by dili on 2016/8/9.
 */
@Subcomponent(modules = VideoModule.class)
public interface VideoComponent {
    VideoListFragmentComponent plus(VideoListFragmentModule module);
}
