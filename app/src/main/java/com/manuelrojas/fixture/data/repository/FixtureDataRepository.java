package com.manuelrojas.fixture.data.repository;

import android.util.Log;

import com.manuelrojas.fixture.data.entity.api.FixtureApi;
import com.manuelrojas.fixture.data.entity.mapper.FixtureApiDataMapper;
import com.manuelrojas.fixture.data.repository.datasource.ILocalFixtureDataStore;
import com.manuelrojas.fixture.data.repository.datasource.sync.IRemoteFixtureDataStore;
import com.manuelrojas.fixture.data.utils.NetworkUtil;
import com.manuelrojas.fixture.domain.Fixture;
import com.manuelrojas.fixture.domain.FixtureRepository;
import com.manuelrojas.fixture.data.entity.FixtureEntity;
import com.manuelrojas.fixture.data.entity.mapper.FixtureEntityDataMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class FixtureDataRepository implements FixtureRepository {

    private final ILocalFixtureDataStore localFixtureDataStore;
    private final IRemoteFixtureDataStore remoteFixtureDataStore;
    private final FixtureEntityDataMapper fixtureEntityDataMapper;
    private final FixtureApiDataMapper fixtureApiDataMapper;

    @Inject
    NetworkUtil networkUtil;

    @Inject
    FixtureDataRepository( ILocalFixtureDataStore fixtureDataStore,
                           IRemoteFixtureDataStore remoteFixtureDataStore,
                           FixtureEntityDataMapper fixtureEntityDataMapper,
                           FixtureApiDataMapper fixtureApiDataMapper) {

        this.localFixtureDataStore = fixtureDataStore;
        this.remoteFixtureDataStore = remoteFixtureDataStore;
        this.fixtureEntityDataMapper = fixtureEntityDataMapper;
        this.fixtureApiDataMapper = fixtureApiDataMapper;
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
        return localFixtureDataStore.findById(id).map(t -> fixtureEntityDataMapper.transform(t));
    }

    private Observable<List<FixtureEntity>> getFixturesFromDb() {
        return localFixtureDataStore.findAll()
                .doOnNext(t -> Log.d("getFixturesFromDb", t.size() + ""));
    }

    private Observable<List<FixtureEntity>> getFixturesFromApi(int type) {
        return remoteFixtureDataStore.syncFixtures(type)
                .flatMap(fixtureApis -> setFixtures(fixtureApis))
                .doOnNext(t -> Log.d("getFixturesFromApi", t.size() + ""));
    }

    private Observable<List<FixtureEntity>> setFixtures(List<FixtureApi> fixtureApis) {
        return Observable.create(new ObservableOnSubscribe<List<FixtureEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<FixtureEntity>> emitter) {
                if (emitter.isDisposed()) return;
                List<FixtureEntity> fixtureEntities = new ArrayList<>();
                for (FixtureApi fixtureApi : fixtureApis) {
                    FixtureEntity fixtureEntity = fixtureApiDataMapper.transformFixtureApi(fixtureApi);
                    long result = localFixtureDataStore.save(fixtureEntity);
                    if ( result > 0)
                        fixtureEntities.add(fixtureEntity);
                }
                emitter.onNext(fixtureEntities);
                emitter.onComplete();
            }
        });
    }

}