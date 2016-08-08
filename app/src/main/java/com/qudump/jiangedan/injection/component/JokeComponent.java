package com.qudump.jiangedan.injection.component;

import com.qudump.jiangedan.injection.module.JokeListFragmentModule;
import com.qudump.jiangedan.injection.module.JokeModule;

import dagger.Subcomponent;

/**
 * Created by dili on 2016/8/8.
 */
@Subcomponent(modules = JokeModule.class)
public interface JokeComponent {
    JokeListFragmentComponent plus(JokeListFragmentModule module);
}
