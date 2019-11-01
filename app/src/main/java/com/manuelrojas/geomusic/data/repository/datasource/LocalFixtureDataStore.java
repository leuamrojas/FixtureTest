package com.manuelrojas.geomusic.data.repository.datasource;

import com.manuelrojas.geomusic.data.entity.FixtureEntity;
import com.manuelrojas.geomusic.data.repository.datasource.db.FixtureDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LocalFixtureDataStore implements FixtureDataStore {

    private FixtureDao fixtureDao;

    @Inject
    public LocalFixtureDataStore(FixtureDao fixtureDao) {
        this.fixtureDao = fixtureDao;
    }

    @Override
    public Observable<List<FixtureEntity>> findAll() {
        return fixtureDao.getFixtures();
    }

    @Override
    public Observable<FixtureEntity> findById(String id) {
        return fixtureDao.getFixture(id);
    }

    @Override
    public long save(FixtureEntity fixtureEntity) {
        return fixtureDao.insert(fixtureEntity);
    }

    @Override
    public void update(FixtureEntity fixtureEntity) {
        fixtureDao.update(fixtureEntity);
    }
}
