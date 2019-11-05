package com.manuelrojas.fixture.data.repository.network;

import com.manuelrojas.fixture.data.entity.api.FixtureApi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FixtureAPiInterface {

    @GET("fixtures.json")
    Observable<List<FixtureApi>> getFixtures();

    @GET("results.json")
    Observable<List<FixtureApi>> getResults();
}
