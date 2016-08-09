package com.qudump.jiangedan.ui;

import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.qudump.jiangedan.injection.component.AppComponent;
import com.qudump.jiangedan.injection.component.BoringPicComponent;
import com.qudump.jiangedan.injection.component.DaggerAppComponent;
import com.qudump.jiangedan.injection.component.GirlPicComponent;
import com.qudump.jiangedan.injection.component.JokeComponent;
import com.qudump.jiangedan.injection.component.PostComponent;
import com.qudump.jiangedan.injection.component.VideoComponent;
import com.qudump.jiangedan.injection.module.AppModule;
import com.qudump.jiangedan.injection.module.BoringPicModule;
import com.qudump.jiangedan.injection.module.GirlPicModule;
import com.qudump.jiangedan.injection.module.JokeModule;
import com.qudump.jiangedan.injection.module.PostModule;
import com.qudump.jiangedan.injection.module.VideoModule;

/**
 * Created by dili on 2016/8/3.
 */
public class BaseApplication extends MultiDexApplication {
    private AppComponent appComponent;
    private PostComponent postComponent;
    private JokeComponent jokeComponent;
    private GirlPicComponent girlPicComponent;
    private BoringPicComponent boringPicComponent;
    private VideoComponent videoComponent;

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

    public JokeComponent buildJokeComponent(){
        jokeComponent = appComponent.plus(new JokeModule());
        return jokeComponent;
    }

    public void releaseJokeComponent() {
        jokeComponent = null;
    }

    public GirlPicComponent buildGirlPicComponent(){
        girlPicComponent = appComponent.plus(new GirlPicModule());
        return girlPicComponent;
    }

    public void releaseGirlPicComponent(){
        girlPicComponent = null;
    }

    public BoringPicComponent buildBoringPicComponent(){
        boringPicComponent = appComponent.plus(new BoringPicModule());
        return boringPicComponent;
    }

    public void releasePicComponent(){
        boringPicComponent = null;
    }

    public VideoComponent buildVideoComponent(){
        videoComponent = appComponent.plus(new VideoModule());
        return videoComponent;
    }

    public void releaseVideoComponent(){
        videoComponent = null;
    }
}
