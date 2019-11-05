package com.manuelrojas.fixture.data.repository.datasource;

import com.manuelrojas.fixture.data.entity.FixtureEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface FixtureDataStore {

    Observable<List<FixtureEntity>> findAll();

    Observable<FixtureEntity> findById(String id);

    long save(FixtureEntity fixtureEntity);

    void update(FixtureEntity fixtureEntity);

    Completable deleteAllFixtures();
}
