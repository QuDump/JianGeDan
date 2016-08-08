package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.GirlPicListFragmentModule;
import com.qudump.jiangedan.ui.fragment.GirlPicListFragment;

import dagger.Subcomponent;

/**
 * Created by dili on 2016/8/8.
 */
@Subcomponent(modules = GirlPicListFragmentModule.class)
public interface GirlPicListFragmentComponent {
    void inject(GirlPicListFragment fragment);
}
