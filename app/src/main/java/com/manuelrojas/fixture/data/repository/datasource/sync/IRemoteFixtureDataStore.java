package com.manuelrojas.fixture.data.repository.datasource.sync;

import com.manuelrojas.fixture.data.entity.api.FixtureApi;

import java.util.List;

import io.reactivex.Observable;

public interface IRemoteFixtureDataStore {

    Observable<List<FixtureApi>> syncFixtures(int type);

}
