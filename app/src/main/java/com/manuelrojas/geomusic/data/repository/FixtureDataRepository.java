package com.manuelrojas.geomusic.data.repository;

import android.util.Log;

import com.manuelrojas.geomusic.data.entity.FixtureEntity;
import com.manuelrojas.geomusic.data.entity.mapper.FixtureEntityDataMapper;
import com.manuelrojas.geomusic.data.repository.datasource.FixtureDataStore;
import com.manuelrojas.geomusic.data.repository.datasource.sync.SyncApiFixture;
import com.manuelrojas.geomusic.domain.Fixture;
import com.manuelrojas.geomusic.domain.FixtureRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FixtureDataRepository implements FixtureRepository {

    private FixtureDataStore fixtureDataStore;
    private SyncApiFixture syncApiFixture;
    private FixtureEntityDataMapper fixtureEntityDataMapper;

    @Inject
    FixtureDataRepository(FixtureDataStore fixtureDataStore, FixtureEntityDataMapper mapper,
                          SyncApiFixture syncApiFixture) {
        this.fixtureDataStore = fixtureDataStore;
        this.fixtureEntityDataMapper = mapper;
        this.syncApiFixture = syncApiFixture;
    }

    @Override
    public Observable<List<Fixture>> getFixtures(int type) {
        return getFixturesFromApi(type)
                .map(t -> fixtureEntityDataMapper.transform(t))
                .doOnNext(t -> Log.d("FixtureDataRepository",  t.size()+"") );
    }

    @Override
    public Observable<Fixture> getFixture(String id) {
        return fixtureDataStore.findById(id).map(t -> fixtureEntityDataMapper.transform(t));
    }

    private Observable<List<FixtureEntity>> getFixturesFromDb() {
        return fixtureDataStore.findAll();
    }

    private Observable<List<FixtureEntity>> getFixturesFromApi(int type) {
        return syncApiFixture.syncFixtures(type);
    }
}
