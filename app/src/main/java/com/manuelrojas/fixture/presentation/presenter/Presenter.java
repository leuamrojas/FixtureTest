package com.manuelrojas.fixture.presentation.presenter;

import com.manuelrojas.fixture.presentation.view.LoadDataView;

public interface Presenter <T extends LoadDataView> {

    void attachView(T view);

    void detachView();
}
