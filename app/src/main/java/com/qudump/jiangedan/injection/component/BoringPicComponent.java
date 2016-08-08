package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.BoringPicListFragmentModule;
import com.qudump.jiangedan.injection.module.BoringPicModule;

import dagger.Subcomponent;

/**
 * Created by qidong on 2016/8/8.
 */
@Subcomponent(modules = BoringPicModule.class)
public interface BoringPicComponent {
    BoringPicListFragmentComponent plus(BoringPicListFragmentModule module);
}
