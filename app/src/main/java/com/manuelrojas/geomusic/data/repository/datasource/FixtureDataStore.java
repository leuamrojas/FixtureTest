package com.manuelrojas.geomusic.data.repository.datasource;

import com.manuelrojas.geomusic.data.entity.FixtureEntity;

import java.util.List;

import io.reactivex.Observable;

public interface FixtureDataStore {

    Observable<List<FixtureEntity>> findAll();

    Observable<FixtureEntity> findById(String id);

    long save(FixtureEntity fixtureEntity);

    void update(FixtureEntity fixtureEntity);
}
