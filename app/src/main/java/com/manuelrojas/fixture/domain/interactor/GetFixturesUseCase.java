package com.manuelrojas.fixture.domain.interactor;

import com.manuelrojas.fixture.domain.Fixture;
import com.manuelrojas.fixture.domain.FixtureRepository;
import com.manuelrojas.fixture.domain.executor.PostExecutionThread;
import com.manuelrojas.fixture.domain.executor.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetFixturesUseCase extends UseCase<List<Fixture>, GetFixturesUseCase.Params> {

    private final FixtureRepository repository;

    @Inject
    public GetFixturesUseCase(FixtureRepository repository, ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<List<Fixture>> buildUseCaseObservable(Params params) {
        return repository.getFixtures(params.type);
    }

    public static final class Params {

        private final int type; //Fixture or Result

        private Params(int type) {
            this.type = type;
        }

        public static Params buildParams(int type) {
            return new Params(type);
        }
    }

}
