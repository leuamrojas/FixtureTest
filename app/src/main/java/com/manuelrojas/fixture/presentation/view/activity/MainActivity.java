package com.manuelrojas.fixture.presentation.view.activity;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navigator.navigateToFixtureList(this);
        finish();
    }

}