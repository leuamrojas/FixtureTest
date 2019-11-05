package com.manuelrojas.fixture.presentation;

import android.app.Application;

import com.manuelrojas.fixture.BuildConfig;
import com.manuelrojas.fixture.presentation.di.component.DaggerApplicationComponent;
import com.manuelrojas.fixture.presentation.di.module.RoomModule;
import com.manuelrojas.fixture.presentation.di.module.ApplicationModule;
import com.manuelrojas.fixture.presentation.di.component.ApplicationComponent;

import net.danlew.android.joda.JodaTimeAndroid;

public class MainApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildApplicationComponent();
//        Stetho.initializeWithDefaults(this);
        JodaTimeAndroid.init(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void buildApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
//            LeakCanary.install(this);
        }
    }

}
