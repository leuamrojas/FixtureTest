package com.manuelrojas.geomusic.presentation.presenter;

import android.util.Log;

import com.manuelrojas.geomusic.domain.Fixture;
import com.manuelrojas.geomusic.domain.exception.DefaultErrorBundle;
import com.manuelrojas.geomusic.domain.exception.ErrorBundle;
import com.manuelrojas.geomusic.domain.interactor.DefaultObserver;
import com.manuelrojas.geomusic.domain.interactor.GetFixturesUseCase;
import com.manuelrojas.geomusic.presentation.exception.ErrorMessageFactory;
import com.manuelrojas.geomusic.presentation.mapper.FixtureModelDataMapper;
import com.manuelrojas.geomusic.presentation.model.FixtureModel;
import com.manuelrojas.geomusic.presentation.view.FixtureListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class FixtureListPresenter implements Presenter<FixtureListView> {

    private static final String TAG = "FixtureListPresenter";
    private FixtureListView view;
    private final GetFixturesUseCase getFixturesUseCase;
    private final FixtureModelDataMapper fixtureModelDataMapper;
    private int page;

    @Inject
    public FixtureListPresenter(GetFixturesUseCase getFixturesUseCase,
                                FixtureModelDataMapper fixtureModelDataMapper) {
        this.getFixturesUseCase = getFixturesUseCase;
        this.fixtureModelDataMapper = fixtureModelDataMapper;
    }

    @Override
    public void attachView(FixtureListView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        getFixturesUseCase.dispose();
    }

    public void loadFixtures(int fixtureType) {
            hideViewRetry();
            showViewLoading();
        getFixturesUseCase.execute(new GetFixturessObserver(),
                GetFixturesUseCase.Params.buildParams(fixtureType));
    }

    private void showViewLoading() {
        view.showLoading();
    }

    private void hideViewLoading() {
        view.hideLoading();
    }

    private void showViewRetry() {
        view.showRetry();
    }

    private void hideViewRetry() {
        view.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(view.context(),
                errorBundle.getException());
        view.showError(errorMessage);
    }

    private void showFixtureCollectionInView(Collection<Fixture> fixtureCollection) {
        final Collection<FixtureModel> fixtureModelCollection =
                fixtureModelDataMapper.transform(fixtureCollection);
        view.renderFixtureList(fixtureModelCollection);
    }

    private final class GetFixturessObserver extends DefaultObserver<List<Fixture>> {

        @Override
        public void onNext(List<Fixture> fixtures) {
            showFixtureCollectionInView(fixtures);
            hideViewLoading();
        }

        @Override
        public void onComplete() {
            hideViewLoading();

        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showViewRetry();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
        }
    }


}
