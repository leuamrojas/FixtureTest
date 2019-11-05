package com.manuelrojas.fixture.presentation.di.component;

import android.content.Context;

import com.manuelrojas.fixture.data.utils.SharedPrefsUtil;
import com.manuelrojas.fixture.domain.FixtureRepository;
import com.manuelrojas.fixture.domain.executor.PostExecutionThread;
import com.manuelrojas.fixture.domain.executor.ThreadExecutor;
import com.manuelrojas.fixture.presentation.view.activity.BaseActivity;
import com.manuelrojas.fixture.presentation.di.module.ApplicationModule;
import com.manuelrojas.fixture.presentation.di.module.RoomModule;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class} )
public interface ApplicationComponent {

    void inject (BaseActivity activity);

    //Exposed to sub-graphs.
    Context getApplication();
    SharedPrefsUtil getSharedPrefsUtil();
    Cache getOkHttpCache();
    Gson getGson();
    OkHttpClient getOkHttpClient();
//    Retrofit getRetrofit();
    Picasso getPicasso();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    FixtureRepository fixtureRepository();
}
