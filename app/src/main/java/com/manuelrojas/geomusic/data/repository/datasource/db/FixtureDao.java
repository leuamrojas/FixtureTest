package com.manuelrojas.geomusic.data.repository.datasource.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.manuelrojas.geomusic.data.entity.FixtureEntity;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface FixtureDao extends BaseDao<FixtureEntity> {

    @Query(value = "SELECT * FROM fixture")
    Observable<List<FixtureEntity>> getFixtures();

    @Query(value = "SELECT * FROM fixture WHERE id = :id")
    Observable<FixtureEntity> getFixture(String id);

    @Query("DELETE FROM fixture")
    public void deleteFixtures();
}
