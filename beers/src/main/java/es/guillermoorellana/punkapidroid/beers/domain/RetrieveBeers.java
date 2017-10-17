package es.guillermoorellana.punkapidroid.beers.domain;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import es.guillermoorellana.core.domain.Interactor;
import es.guillermoorellana.punkapidroid.beers.data.BeerRepository;
import es.guillermoorellana.punkapidroid.beers.data.db.entity.DbBeer;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerEntry;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

@Interactor
public class RetrieveBeers {

    @NonNull
    private final BeerRepository beerRepository;
    @NonNull
    private final Function<DbBeer, BeerEntry> modelMapper;

    @Inject
    RetrieveBeers(@NonNull BeerRepository beerRepository,
                  @NonNull Function<DbBeer, BeerEntry> modelMapper) {
        this.beerRepository = beerRepository;
        this.modelMapper = modelMapper;
    }

    @NonNull
    public Flowable<List<BeerEntry>> getStream() {
        return beerRepository.getAll()
                .flatMap(list ->
                        Flowable.fromIterable(list)
                                .map(modelMapper)
                                .toList()
                                .toFlowable()
                );
    }
}
