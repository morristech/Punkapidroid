package es.guillermoorellana.punkapidroid.beers.domain;

import android.support.annotation.NonNull;

import com.fernandocejas.arrow.optional.Optional;

import java.util.List;

import es.guillermoorellana.core.domain.Interactor;
import es.guillermoorellana.core.rx.UnwrapOptionTransformer;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerEntry;
import es.guillermoorellana.punkapidroid.beers.data.BeerRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Interactor
public class RetrieveBeers {

    @NonNull
    private BeerRepository beerRepository;

    public RetrieveBeers(@NonNull BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @NonNull
    public Flowable<List<BeerEntry>> getStream() {
        return beerRepository.getAllBeers()
                .flatMapSingle(this::fetchWhenNoneAndThenBeers)
                .compose(UnwrapOptionTransformer.create());
    }

    @NonNull
    private Single<Optional<List<BeerEntry>>> fetchWhenNoneAndThenBeers(@NonNull final Optional<List<BeerEntry>> beers) {
        return fetchWhenNone(beers).andThen(Single.just(beers));
    }

    @NonNull
    private Completable fetchWhenNone(@NonNull final Optional<List<BeerEntry>> beers) {
        return beers.isPresent()
                ? beerRepository.fetchBeers()
                : Completable.complete();
    }
}
