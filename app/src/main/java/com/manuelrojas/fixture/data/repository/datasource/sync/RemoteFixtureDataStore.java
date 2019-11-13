package com.manuelrojas.fixture.data.repository.datasource.sync;

import com.manuelrojas.fixture.data.repository.network.RetrofitClient;
import com.manuelrojas.fixture.data.entity.api.FixtureApi;
import com.manuelrojas.fixture.data.repository.network.FixtureAPiInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoteFixtureDataStore implements IRemoteFixtureDataStore{

    private RetrofitClient client;
//    private ILocalFixtureDataStore fixtureDataStore;
//    private FixtureApiDataMapper fixtureEntityApiMapper;

    @Inject
    public RemoteFixtureDataStore(RetrofitClient client) {
        this.client = client;
//        this.fixtureDataStore = fixtureDataStore;
//        this.fixtureEntityApiMapper = fixtureEntityApiMapper;
    }

    @Override
    public Observable<List<FixtureApi>> syncFixtures(int type) {
        if (type==0)
            return client.getRetrofit().create(FixtureAPiInterface.class).getFixtures();
        else
            return client.getRetrofit().create(FixtureAPiInterface.class).getResults();
    }

//    private RetrofitClient client;
//    private ILocalFixtureDataStore fixtureDataStore;
//    private FixtureApiDataMapper fixtureEntityApiMapper;
//
//    @Inject
//    public RemoteFixtureDataStore(RetrofitClient client, ILocalFixtureDataStore fixtureDataStore,
//                                  FixtureApiDataMapper fixtureEntityApiMapper) {
//        this.client = client;
//        this.fixtureDataStore = fixtureDataStore;
//        this.fixtureEntityApiMapper = fixtureEntityApiMapper;
//    }
//
//    public Observable<List<FixtureEntity>> syncFixtures(int type) {
//        FixtureAPiInterface api = client.getRetrofit().create(FixtureAPiInterface.class);
//        Observable<List<FixtureApi>> fixtureApisObservable;
//        if (type==0)
//            fixtureApisObservable = api.getFixtures();
//        else
//            fixtureApisObservable = api.getResults();
//        return fixtureApisObservable
//                .flatMap(fixtureApis -> setFixtures(fixtureApis));
//    }
//
//    private Observable<List<FixtureEntity>> setFixtures(List<FixtureApi> fixtureApis) {
//        return Observable.create(new ObservableOnSubscribe<List<FixtureEntity>>() {
//            @Override
//            public void subscribe(ObservableEmitter<List<FixtureEntity>> emitter) throws Exception {
//                if (emitter.isDisposed()) return;
//                List<FixtureEntity> fixtureEntities = new ArrayList<>();
//                for (FixtureApi fixtureApi : fixtureApis) {
//                    FixtureEntity fixtureEntity = fixtureEntityApiMapper.transformFixtureApi(fixtureApi);
//                    long result = fixtureDataStore.save(fixtureEntity);
//                    if ( result > 0)
//                        fixtureEntities.add(fixtureEntity);
//                }
//                emitter.onNext(fixtureEntities);
//                emitter.onComplete();
//            }
//        });
//    }

}
