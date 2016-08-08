package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.GirlPicListFragmentModule;
import com.qudump.jiangedan.injection.module.GirlPicModule;

import dagger.Subcomponent;

/**
 * Created by dili on 2016/8/8.
 */
@Subcomponent(modules = GirlPicModule.class)
public interface GirlPicComponent {
    GirlPicListFragmentComponent plus(GirlPicListFragmentModule module);
}
