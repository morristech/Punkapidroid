package es.guillermoorellana.punkapidroid.beers.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import es.guillermoorellana.core.domain.Interactor;
import es.guillermoorellana.punkapidroid.beers.data.BeerRepository;
import es.guillermoorellana.punkapidroid.beers.data.db.entity.Beer;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerEntry;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.processors.BehaviorProcessor;

@Interactor
public class SearchBeers {

    @NonNull
    private final RetrieveBeers retrieveBeers;
    @NonNull
    private final BeerRepository repository;
    @NonNull
    private final Function<Beer, BeerEntry> modelMapper;
    @NonNull
    private final BehaviorProcessor<Flowable<List<BeerEntry>>> processor = BehaviorProcessor.create();

    @Inject
    public SearchBeers(@NonNull RetrieveBeers retrieveBeers,
                       @NonNull BeerRepository repository,
                       @NonNull Function<Beer, BeerEntry> modelMapper) {
        this.retrieveBeers = retrieveBeers;
        this.repository = repository;
        this.modelMapper = modelMapper;

        processor.onNext(retrieveBeers.getStream());
    }

    @NonNull
    public Flowable<List<BeerEntry>> getStream() {
        return Flowable.switchOnNext(processor);
    }

    public void setSearchQuery(@Nullable String query) {
        if (query == null) {
            processor.onNext(retrieveBeers.getStream());
        } else {
            processor.onNext(repository.queryByName(query).flatMap(this::mapEntries));
        }
    }

    private Flowable<List<BeerEntry>> mapEntries(List<Beer> list) {
        return Flowable.fromIterable(list)
                .map(modelMapper)
                .toList()
                .toFlowable();
    }
}
