package com.qudump.jiangedan.ui.fragment;

import com.qudump.jiangedan.injection.module.BoringPicListFragmentModule;
import com.qudump.jiangedan.presenter.BoringPicListPresenter;

import javax.inject.Inject;

/**
 * Created by qidong on 2016/8/8.
 */
public class BoringPicListFragment extends AbstractBaseFragment {
    @Inject
    BoringPicListPresenter presenter;
    @Override
    void initDagger() {
        getApplication()
                .buildBoringPicComponent()
                .plus(new BoringPicListFragmentModule())
                .inject(this);
    }
}
