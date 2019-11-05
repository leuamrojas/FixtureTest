package com.manuelrojas.fixture.presentation.di.module;

import android.app.Application;

import androidx.room.Room;

import com.manuelrojas.fixture.data.repository.datasource.FixtureDataStore;
import com.manuelrojas.fixture.data.repository.datasource.LocalFixtureDataStore;
import com.manuelrojas.fixture.data.repository.datasource.db.FixtureDao;
import com.manuelrojas.fixture.data.repository.datasource.db.FixtureDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private FixtureDatabase fixtureDatabase;

    public RoomModule(Application mApplication) {
        fixtureDatabase = Room.databaseBuilder(mApplication, FixtureDatabase.class, "fixture-db").build();
    }

    @Singleton
    @Provides
    FixtureDatabase providesRoomDatabase() {
        return fixtureDatabase;
    }

    @Singleton
    @Provides
    FixtureDao providesFixtureDao(FixtureDatabase database) {
        return database.getFixtureDao();
    }

    @Singleton
    @Provides
    FixtureDataStore providesFixtureDataStore(FixtureDao fixtureDao) {
        return new LocalFixtureDataStore(fixtureDao);
    }

}