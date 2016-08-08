package com.qudump.jiangedan.injection.module;

import com.qudump.jiangedan.net.service.girlpic.GirlPicApiService;
import com.qudump.jiangedan.net.service.girlpic.GirlPicApiServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dili on 2016/8/8.
 */
@Module
public class GirlPicModule {
    @Provides
    GirlPicApiService providesGirlPicApiService(GirlPicApiServiceImpl girlPicApiService) {
        return girlPicApiService;
    }
}
