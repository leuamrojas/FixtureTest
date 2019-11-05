package com.manuelrojas.fixture.data.repository.datasource.sync;

import com.manuelrojas.fixture.data.repository.network.RetrofitClient;
import com.manuelrojas.fixture.data.entity.FixtureEntity;
import com.manuelrojas.fixture.data.entity.api.FixtureApi;
import com.manuelrojas.fixture.data.entity.mapper.FixtureEntityApiMapper;
import com.manuelrojas.fixture.data.repository.datasource.FixtureDataStore;
import com.manuelrojas.fixture.data.repository.network.FixtureAPiInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class SyncApiFixture {

    private RetrofitClient client;
    private FixtureDataStore fixtureDataStore;
    private FixtureEntityApiMapper fixtureEntityApiMapper;

    @Inject
    public SyncApiFixture(RetrofitClient client, FixtureDataStore fixtureDataStore,
                        FixtureEntityApiMapper fixtureEntityApiMapper) {
        this.client = client;
        this.fixtureDataStore = fixtureDataStore;
        this.fixtureEntityApiMapper = fixtureEntityApiMapper;
    }

    public Observable<List<FixtureEntity>> syncFixtures(int type) {
        FixtureAPiInterface api = client.getRetrofit().create(FixtureAPiInterface.class);
        Observable<List<FixtureApi>> fixtureApisObservable;
        if (type==0)
            fixtureApisObservable = api.getFixtures();
        else
            fixtureApisObservable = api.getResults();
        return fixtureApisObservable
                .flatMap(fixtureApis -> setFixtures(fixtureApis));
    }

    private Observable<List<FixtureEntity>> setFixtures(List<FixtureApi> fixtureApis) {
        return Observable.create(new ObservableOnSubscribe<List<FixtureEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<FixtureEntity>> emitter) throws Exception {
                if (emitter.isDisposed()) return;
                List<FixtureEntity> fixtureEntities = new ArrayList<>();
                for (FixtureApi fixtureApi : fixtureApis) {
                    FixtureEntity fixtureEntity = fixtureEntityApiMapper.transformFixtureApi(fixtureApi);
                    long result = fixtureDataStore.save(fixtureEntity);
                    if ( result > 0)
                        fixtureEntities.add(fixtureEntity);
                }
                emitter.onNext(fixtureEntities);
                emitter.onComplete();
            }
        });
    }

}
