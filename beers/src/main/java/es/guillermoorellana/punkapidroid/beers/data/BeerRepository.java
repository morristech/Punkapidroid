package es.guillermoorellana.punkapidroid.beers.data;

import android.arch.persistence.room.EmptyResultSetException;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import es.guillermoorellana.punkapidroid.beers.data.db.BeersDao;
import es.guillermoorellana.punkapidroid.beers.data.db.entity.DbBeer;
import es.guillermoorellana.punkapidroid.beers.data.net.BeerNetworkService;
import es.guillermoorellana.punkapidroid.beers.data.net.entity.NetBeer;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class BeerRepository {
    private static final int BEERS_PER_PAGE = 50;

    @NonNull private final BeerNetworkService network;
    @NonNull private final BeersDao dao;
    @NonNull private final Function<NetBeer, DbBeer> mapper;

    @Inject
    BeerRepository(
            @NonNull BeerNetworkService network,
            @NonNull BeersDao dao,
            @NonNull Function<NetBeer, DbBeer> mapper) {
        this.network = network;
        this.dao = dao;
        this.mapper = mapper;
    }

    public Flowable<List<DbBeer>> getAll() {
        return fetchAndMap()
                .publish(data -> Flowable.merge(data, dao.getAllStream().takeUntil(data)))
                .retryWhen(guard -> guard.flatMap(
                        (Throwable error) -> {
                            if (error instanceof EmptyResultSetException) {
                                return fetchAndMap();
                            } else {
                                return Flowable.error(error);
                            }
                        })
                );
    }

    private Flowable<List<DbBeer>> fetchAndMap() {
        return network.getBeers(BEERS_PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMap(netBeers -> Flowable.fromIterable(netBeers).map(mapper).toList())
                .doOnSuccess(dao::insert)
                .toFlowable();
    }
}
