package com.manuelrojas.fixture.presentation.di.component;

import android.app.Activity;

import com.manuelrojas.fixture.presentation.di.module.ActivityModule;
import com.manuelrojas.fixture.presentation.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    //Exposed to sub-graphs
    Activity activity();

}
