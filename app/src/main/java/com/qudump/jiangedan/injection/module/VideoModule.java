package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.net.service.littlevideo.LittleVideoApiService;
import com.qudump.jiangedan.net.service.littlevideo.LittleVideoApiServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dili on 2016/8/9.
 */
@Module
public class VideoModule {
    @Provides
    LittleVideoApiService providesLittleVideoApiService(LittleVideoApiServiceImpl littleVideoApiService){
        return littleVideoApiService;
    }
}
