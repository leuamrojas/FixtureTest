package com.manuelrojas.geomusic.data.repository.datasource.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.manuelrojas.geomusic.data.entity.FixtureEntity;

@Database(entities = {FixtureEntity.class}, version = FixtureDatabase.VERSION)
public abstract class FixtureDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract FixtureDao getFixtureDao();

}
