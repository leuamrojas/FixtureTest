package com.manuelrojas.geomusic.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.manuelrojas.geomusic.presentation.view.activity.FixtureListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {}

    public void navigateToFixtureList(Context context) {
        if (context != null) {
            Intent intentToLaunch = FixtureListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

}