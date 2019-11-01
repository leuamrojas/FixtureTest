package com.manuelrojas.geomusic.presentation.view;

import com.manuelrojas.geomusic.presentation.model.FixtureModel;

import java.util.Collection;

public interface FixtureListView extends LoadDataView{

    void renderFixtureList(Collection<FixtureModel> fixtureModelCollection);

    void showRetry();

    void hideRetry();

}