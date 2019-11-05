package com.manuelrojas.fixture.presentation.di.component;

import com.manuelrojas.fixture.presentation.di.module.ActivityModule;
import com.manuelrojas.fixture.presentation.di.scope.PerActivity;
import com.manuelrojas.fixture.presentation.view.fragment.FixtureListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface FixtureComponent {

      void inject(FixtureListFragment fragment);

}