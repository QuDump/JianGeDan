package com.qudump.jiangedan.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.qudump.jiangedan.ui.BaseApplication;

/**
 * Created by dili on 2016/8/8.
 */
public abstract class AbstractBaseFragment extends Fragment{

    abstract void initDagger();
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected BaseApplication getApplication(){
        return (BaseApplication)mContext.getApplicationContext();
    }

    protected Context getActivityContext() {
        return mContext;
    }
}
