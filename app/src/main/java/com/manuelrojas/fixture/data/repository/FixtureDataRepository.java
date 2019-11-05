package com.manuelrojas.fixture.data.repository;

import android.util.Log;

import com.manuelrojas.fixture.data.repository.datasource.sync.SyncApiFixture;
import com.manuelrojas.fixture.data.utils.NetworkUtil;
import com.manuelrojas.fixture.domain.Fixture;
import com.manuelrojas.fixture.domain.FixtureRepository;
import com.manuelrojas.fixture.data.entity.FixtureEntity;
import com.manuelrojas.fixture.data.entity.mapper.FixtureEntityDataMapper;
import com.manuelrojas.fixture.data.repository.datasource.FixtureDataStore;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class FixtureDataRepository implements FixtureRepository {

    private FixtureDataStore fixtureDataStore;
    private SyncApiFixture syncApiFixture;
    private FixtureEntityDataMapper fixtureEntityDataMapper;

    @Inject
    NetworkUtil networkUtil;

    @Inject
    FixtureDataRepository(FixtureDataStore fixtureDataStore, FixtureEntityDataMapper mapper,
                          SyncApiFixture syncApiFixture) {
        this.fixtureDataStore = fixtureDataStore;
        this.fixtureEntityDataMapper = mapper;
        this.syncApiFixture = syncApiFixture;
    }

    @Override
    public Observable<List<Fixture>> getFixtures(int type) {
        if (networkUtil.isNetworkConnected()) {
            return getFixturesFromApi(type)
                    .timeout(5, TimeUnit.SECONDS, getFixturesFromDb())
                    .map(t -> fixtureEntityDataMapper.transform(t))
                    .doOnNext(t -> Log.d("FixtureDataRepository1", t.size() + ""));
        } else {
            return getFixturesFromDb()
                    .map(t -> fixtureEntityDataMapper.transform(t))
                    .doOnNext(t -> Log.d("FixtureDataRepository2", t.size() + ""));
        }
//            return Observable.concatArrayDelayError(getFixturesFromDb(), getFixturesFromApi(type))
//                    .map(t -> fixtureEntityDataMapper.transform(t))
//                    .doOnNext(t -> Log.d("FixtureDataRepository2", t.size() + ""));

    }

    @Override
    public Observable<Fixture> getFixture(String id) {
        return fixtureDataStore.findById(id).map(t -> fixtureEntityDataMapper.transform(t));
    }

    private Observable<List<FixtureEntity>> getFixturesFromDb() {
        return fixtureDataStore.findAll()
                .doOnNext(t -> Log.d("getFixturesFromDb", t.size() + ""));
    }

    private Observable<List<FixtureEntity>> getFixturesFromApi(int type) {
        return syncApiFixture.syncFixtures(type)
                .doOnNext(t -> Log.d("getFixturesFromApi", t.size() + ""));
    }

    private Completable deleteFixturesFromDb() {
        return fixtureDataStore.deleteAllFixtures();
    }
}
