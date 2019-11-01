package com.manuelrojas.geomusic.presentation.di.component;

import com.manuelrojas.geomusic.presentation.di.module.ActivityModule;
import com.manuelrojas.geomusic.presentation.di.scope.PerActivity;
import com.manuelrojas.geomusic.presentation.view.fragment.FixtureListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface FixtureComponent {

      void inject(FixtureListFragment fragment);

}