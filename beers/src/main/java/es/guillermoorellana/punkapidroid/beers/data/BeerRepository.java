package es.guillermoorellana.punkapidroid.beers.data;

import android.arch.persistence.room.EmptyResultSetException;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import es.guillermoorellana.punkapidroid.beers.data.db.BeersDao;
import es.guillermoorellana.punkapidroid.beers.data.db.entity.Beer;
import es.guillermoorellana.punkapidroid.beers.data.net.BeerNetworkService;
import es.guillermoorellana.punkapidroid.beers.data.net.entity.NetBeer;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static java.util.Collections.emptyList;

@Singleton
public class BeerRepository {
    private static final int BEERS_PER_PAGE = 50;

    @NonNull
    private final BeerNetworkService network;
    @NonNull
    private final BeersDao dao;
    @NonNull
    private final Function<NetBeer, Beer> mapper;

    @Inject
    BeerRepository(@NonNull BeerNetworkService network,
                   @NonNull BeersDao dao,
                   @NonNull Function<NetBeer, Beer> mapper) {
        this.network = network;
        this.dao = dao;
        this.mapper = mapper;
    }

    @NonNull
    public Flowable<List<Beer>> getAll() {
        return dao.getAllStream()
                .startWith(fetchFromNetworkMapAndInsert());
    }

    private Flowable<List<Beer>> fetchFromNetworkMapAndInsert() {
        return network.getBeers(BEERS_PER_PAGE)
                .onErrorReturn(__ -> emptyList())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMap(netBeers -> Flowable.fromIterable(netBeers).map(mapper).toList())
                .doOnSuccess(dao::insert)
                .toFlowable();
    }

    @NonNull
    public Flowable<List<Beer>> queryByName(@NonNull String name) {
        return dao.findBeersByName(name)
                .observeOn(Schedulers.io())
                .onErrorResumeNext(error -> {
                    if (error instanceof EmptyResultSetException) {
                        return Flowable.just(emptyList());
                    } else {
                        return Flowable.error(error);
                    }
                });
    }
}
