package com.manuelrojas.fixture.presentation.view;

import com.manuelrojas.fixture.presentation.model.FixtureModel;

import java.util.Collection;

public interface FixtureListView extends LoadDataView{

    void renderFixtureList(Collection<FixtureModel> fixtureModelCollection);

    void showRetry();

    void hideRetry();

}