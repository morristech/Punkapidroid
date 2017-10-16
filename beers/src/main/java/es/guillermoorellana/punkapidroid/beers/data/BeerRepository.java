package es.guillermoorellana.punkapidroid.beers.data;

import android.support.annotation.NonNull;

import com.fernandocejas.arrow.optional.Optional;

import java.util.List;

import es.guillermoorellana.core.data.ReactiveStore;
import es.guillermoorellana.punkapidroid.beers.data.entity.Beer;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerEntry;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class BeerRepository {
    private static final int BEERS_PER_PAGE = 50;

    @NonNull
    private final ReactiveStore<Integer, BeerEntry> beerStore;
    @NonNull
    private final BeerNetworkService beerNetworkService;
    @NonNull
    private final Function<Beer, BeerEntry> beerMapper;

    public BeerRepository(
            @NonNull ReactiveStore<Integer, BeerEntry> beerStore,
            @NonNull BeerNetworkService beerNetworkService,
            @NonNull Function<Beer, BeerEntry> beerMapper) {
        this.beerStore = beerStore;
        this.beerNetworkService = beerNetworkService;
        this.beerMapper = beerMapper;
    }

    public Flowable<Optional<List<BeerEntry>>> getAllBeers() {
        return beerStore.getAll();
    }

    public Completable fetchBeers() {
        return beerNetworkService.getBeers(BEERS_PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMapObservable(Observable::fromIterable)
                .map(beerMapper)
                .toList()
                .doOnSuccess(beerStore::replaceAll)
                .toCompletable();
    }
}
