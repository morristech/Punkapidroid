package es.guillermoorellana.punkapidroid.beers.data.net;

import java.util.List;

import es.guillermoorellana.punkapidroid.beers.data.net.entity.NetBeer;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BeerNetworkService {

    @GET("beers")
    Single<List<NetBeer>> getBeers(@Query("per_page") Integer perPage);
}
