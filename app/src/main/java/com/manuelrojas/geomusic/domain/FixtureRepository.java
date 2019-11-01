package com.manuelrojas.geomusic.domain;

import java.util.List;

import io.reactivex.Observable;

public interface FixtureRepository {

    Observable<List<Fixture>> getFixtures(int type);

    Observable<Fixture> getFixture(String id);
}
