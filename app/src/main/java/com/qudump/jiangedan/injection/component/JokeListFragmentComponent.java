package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.JokeListFragmentModule;
import com.qudump.jiangedan.ui.fragment.JokeListFragment;

import dagger.Subcomponent;

/**
 * Created by dili on 2016/8/8.
 */
@Subcomponent(modules = JokeListFragmentModule.class)
public interface JokeListFragmentComponent {
    void inject(JokeListFragment fragment);
}
