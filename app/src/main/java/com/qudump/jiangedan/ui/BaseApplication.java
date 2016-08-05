package com.qudump.jiangedan.ui;

import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.qudump.jiangedan.injection.component.AppComponent;
import com.qudump.jiangedan.injection.component.DaggerAppComponent;
import com.qudump.jiangedan.injection.component.PostComponent;
import com.qudump.jiangedan.injection.module.AppModule;
import com.qudump.jiangedan.injection.module.PostModule;

/**
 * Created by dili on 2016/8/3.
 */
public class BaseApplication extends MultiDexApplication {
    private AppComponent appComponent;
    private PostComponent postComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildAppComponent();
        Fresco.initialize(this);
    }

    private AppComponent buildAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        return appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public PostComponent buildPostComponent(){
        postComponent = appComponent.plus(new PostModule());
        return postComponent;
    }

    public void releasePostComponent(){
        postComponent = null;
    }
}
