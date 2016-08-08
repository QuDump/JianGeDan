package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.BoringPicListFragmentModule;
import com.qudump.jiangedan.ui.fragment.BoringPicListFragment;

import dagger.Subcomponent;

/**
 * Created by qidong on 2016/8/8.
 */
@Subcomponent(modules = BoringPicListFragmentModule.class)
public interface BoringPicListFragmentComponent {
    void inject(BoringPicListFragment fragment);
}
