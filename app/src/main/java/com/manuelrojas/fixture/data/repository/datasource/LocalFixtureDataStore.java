package com.manuelrojas.fixture.data.repository.datasource;

import com.manuelrojas.fixture.data.repository.datasource.db.FixtureDao;
import com.manuelrojas.fixture.data.entity.FixtureEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class LocalFixtureDataStore implements ILocalFixtureDataStore {

    private FixtureDao fixtureDao;

    @Inject
    public LocalFixtureDataStore(FixtureDao fixtureDao) {
        this.fixtureDao = fixtureDao;
    }

    @Override
    public Observable<List<FixtureEntity>> findAll() {
        return Observable.create(new ObservableOnSubscribe<List<FixtureEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<FixtureEntity>> emitter) {
                emitter.onNext(fixtureDao.getFixtures());
                emitter.onComplete();
            }
        });
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

    @Override
    public Completable deleteAllFixtures() {
        return fixtureDao.deleteFixtures();
    }
}
