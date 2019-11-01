package com.manuelrojas.geomusic.data.repository.network;

import com.manuelrojas.geomusic.data.entity.api.FixtureApi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FixtureAPiInterface {

    @GET("fixtures.json")
    Observable<List<FixtureApi>> getFixtures();

    @GET("results.json")
    Observable<List<FixtureApi>> getResults();
}
